package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.InMemoryCustomerRepository;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Repositories.ProductRepository;
import com.example.softwareassignment2.Services.NotificationHandlers.NotificationSystem;
import com.example.softwareassignment2.Services.NotificationHandlers.SendNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private NotificationSystem notificationSystem;
    @Autowired
    private SendNotification sendNotification;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order placeOrder(OrderRequest orderRequest) throws CloneNotSupportedException {

        int customerID = orderRequest.getCustomerID();
        List<Product> products = new ArrayList<>();

        double totalOrderPrice = 0;
        for (ProductRequest productRequest : orderRequest.getProducts()) {
            Product product = (Product) productService.getProductBySerialNumber(productRequest.getSerialNumber()).clone();
            if (product != null) {
                product.setQuantity(productRequest.getQuantity());
                System.out.println(product.getSerialNumber());
                // product.setOrder(order);
                products.add(product);
                totalOrderPrice += product.getQuantity() * product.getPrice();
            } else {
                System.out.println("Product doesn't exist");
            }
        }

        if (validateCustomerBalance(customerID, totalOrderPrice)) {
            // make and order with sent products and customer id
            Order createdOrder = orderRepository.addOrder(products, customerID, orderRequest.getShippingAddress());

            // reduce product quantity
            if (!productRepository.reduceProductsQuantity(products)) {
                // not enough quantity in the stock
                System.out.println("Not enough quantity in the stock");
                return null;
            }
            createdOrder.setOrderPrice(totalOrderPrice);

            List<String> placeHolders = new ArrayList<>();
            Customer customer = customerRepository.getCustomerByID(customerID);

            placeHolders.add(customer.getUsername());
            for (Product p : products) {
                placeHolders.add(p.getName());
            }

            notificationSystem.createMessage(NotificationType.ORDER_PLACEMENT, placeHolders, customer);
            return createdOrder;

        } else {
            System.out.println("customer id is not valid or customer doesn't have enough balance");
            return null;
        }

    }


    public Order placeCompoundOrder(List<Integer> simpleOrderIds) {
        List<SimpleOrder> simpleOrders = new ArrayList<>();

        // get the simple order from their ids
        for (Integer simpleOrderId : simpleOrderIds) {
            Order requestedOrder = orderRepository.getOrderById(simpleOrderId);
            if (requestedOrder != null)
                simpleOrders.add((SimpleOrder) requestedOrder);
            else {
                System.out.println("order with id " + simpleOrderId + "doesn't exist");
                return null;
            }

            List<String> placeHolders = new ArrayList<>();
            SimpleOrder order = simpleOrders.get(simpleOrderId - 1);

            int id = order.getCustomerID();
            System.out.println(id);

            Customer customer = customerRepository.getCustomerByID(id);
            System.out.println(customer.getUsername());

            for (Product p : simpleOrders.get(simpleOrderId - 1).getProducts()) {
                placeHolders.add(p.getName());
            }

            notificationSystem.createMessage(NotificationType.ORDER_PLACEMENT, placeHolders, customer);


        }

        CompoundOrder createdCompoundOrder = new CompoundOrder(simpleOrders);
        orderRepository.addCompoundOrder(createdCompoundOrder);
        return createdCompoundOrder;
    }


    public boolean validateCustomerBalance(int customerId, double totalPrice) {
        Customer customer = customerRepository.getCustomerByID(customerId);

        if (customer != null && customer.getCustomerAccount().getAccountBalance() >= totalPrice) {
            customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - totalPrice);
            return true;
        } else
            return false;
    }

    public boolean cancelOrder(int orderId) {
        Order order = orderRepository.getOrderById(orderId);

        if (order != null) {
            LocalDateTime orderTime = order.getOrderTime();
            LocalDateTime currentTime = LocalDateTime.now();

            // Calculate the time difference in hours
            long hoursDifference = Duration.between(orderTime, currentTime).toHours();

            // Check if the order was created more than 1 hour ago
            if (hoursDifference <= 1) {
                if (order instanceof SimpleOrder) {
                    // For a SimpleOrder
                    cancelSimpleOrder((SimpleOrder) order);
                } else if (order instanceof CompoundOrder) {
                    // For a CompoundOrder, cancel each SimpleOrder within it
                    List<SimpleOrder> simpleOrders = ((CompoundOrder) order).getSimpleOrders();
                    for (SimpleOrder simpleOrder : simpleOrders) {
                        cancelSimpleOrder(simpleOrder);
                    }
                    orderRepository.cancelOrder(order.getOrderID());

                }

                return true;
            }

            return false;
        } else {
            System.out.println("Order with ID " + orderId + " doesn't exist");
            return false;
        }
    }

    private void cancelSimpleOrder(SimpleOrder simpleOrder) {
        List<Product> products = simpleOrder.getProducts();

        productService.rollbackProductQuantities(products);

        Customer customer = customerRepository.getCustomerByID(simpleOrder.getCustomerID());
        customer.getCustomerAccount().setAccountBalance(
                customer.getCustomerAccount().getAccountBalance() + simpleOrder.getOrderPrice());

        orderRepository.cancelOrder(simpleOrder.getOrderID());

        // if there was a shipment for that order remove it
        removeAssociatedShipment(simpleOrder.getOrderID());
    }

    private void removeAssociatedShipment(int orderId) {
        shipmentService.removeAssociatedShipment(orderId);
    }
}


