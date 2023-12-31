package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.OrderResponse;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.InMemoryCustomerRepository;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Repositories.ProductRepository;
import com.example.softwareassignment2.Services.NotificationHandlers.NotificationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private NotificationSystem notificationSystem;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) throws CloneNotSupportedException {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDetails(null);

        int customerID = orderRequest.getCustomerID();
        List<Product> products = new ArrayList<>();

        double totalOrderPrice = 0;
        for (ProductRequest productRequest : orderRequest.getProducts()) {
            Product product =  (Product) productService.getProductBySerialNumber(productRequest.getSerialNumber()).clone();
            if(product != null){
                product.setQuantity(productRequest.getQuantity());
                System.out.println("quantity = " + product.getQuantity());
                System.out.println(product.getSerialNumber());
                // product.setOrder(order);
                products.add(product);
                totalOrderPrice += product.getQuantity() * product.getPrice();
            }else {
                System.out.println("Product doesn't exist");
                orderResponse.setError("Product doesn't exist");
                return orderResponse;
            }
        }
        Customer user = checkIfUserExists(customerID);
        if(user != null){

            if(!validateCustomerBalance(user, totalOrderPrice)){
                System.out.println("customer doesn't have enough balance to place the order");
                orderResponse.setError("customer doesn't have enough balance to place the order");
                return orderResponse;
            }

            // make and order with sent products and customer id
            Order createdOrder = orderRepository.addOrder(products, customerID, orderRequest.getShippingAddress());

            // reduce product quantity
            if(!productRepository.reduceProductsQuantity(products)){
                // not enough quantity in the stock
                System.out.println("Not enough quantity in stock");
                orderResponse.setError("Not enough quantity for the product ordered in stock");
                return orderResponse;
            }
            createdOrder.setOrderPrice(totalOrderPrice);

            List<String> placeHolders = new ArrayList<>();
            Customer customer = customerRepository.getCustomerByID(customerID);

            placeHolders.add(customer.getUsername());
            for(Product p : products){
                placeHolders.add(p.getName());
            }
            orderResponse.setOrderDetails(createdOrder);

            notificationSystem.sendMessage(NotificationType.ORDER_PLACEMENT, placeHolders, customer);
            return orderResponse;
        }else {
            System.out.println("customer id is not valid customer doesn't exist");
            orderResponse.setError("customer id is not valid customer doesn't exist");
            return orderResponse;
        }

    }


    public Order placeCompoundOrder(List<Integer> simpleOrderIds){
        List<SimpleOrder> simpleOrders = new ArrayList<>();

        // get the simple order from their ids
        for(Integer simpleOrderId : simpleOrderIds){
            Order requestedOrder = orderRepository.getOrderById(simpleOrderId);
            if(requestedOrder != null)
                simpleOrders.add((SimpleOrder) requestedOrder);
            else{
                System.out.println("order with id " + simpleOrderId + "doesn't exist");
                return null;
            }
        }

        CompoundOrder createdCompoundOrder = new CompoundOrder(simpleOrders);
        orderRepository.addCompoundOrder(createdCompoundOrder);
        return createdCompoundOrder;
    }



    private Customer checkIfUserExists(int customerId){
        Customer customer = customerRepository.getCustomerByID(customerId);
        return customer; // null doesn't exist
    }

    private boolean validateCustomerBalance(Customer customer, double totalPrice){
        // Customer customer = customerRepository.getCustomerByID(customerId);

        if(customer != null && customer.getCustomerAccount().getAccountBalance() >= totalPrice){
            customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - totalPrice);
            return true;
        }
        else
            return false;
    }
}
