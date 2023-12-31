package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.InMemoryCustomerRepository;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Services.NotificationHandlers.NotificationSystem;
import com.example.softwareassignment2.Services.NotificationHandlers.SendNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private SendNotification sendNotification;

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public Order placeOrder(OrderRequest orderRequest){

        int customerID = orderRequest.getCustomerID();
        List<Product> products = new ArrayList<>();

        double totalOrderPrice = 0;
        for (ProductRequest productRequest : orderRequest.getProducts()) {
            Product product = productService.getProductBySerialNumber(productRequest.getSerialNumber());
            if(product != null){
                product.setQuantity(productRequest.getQuantity());
                System.out.println(product.getSerialNumber());
                // product.setOrder(order);
                products.add(product);
                totalOrderPrice += product.getQuantity() * product.getPrice();
            }else {
                System.out.println("Product doesn't exist");
            }
        }

        if(validateCustomerBalance(customerID, totalOrderPrice)){
            // make and order with sent products and customer id
            Order createdOrder = orderRepository.addOrder(products, customerID, orderRequest.getShippingAddress());

            createdOrder.setOrderPrice(totalOrderPrice);

            List<String> placeHolders = new ArrayList<>();
            Customer customer = customerRepository.getCustomerByID(customerID);


            for(Product p : products){
                placeHolders.add(p.getName());
            }

            notificationSystem.createMessage(NotificationType.ORDER_PLACEMENT, placeHolders, customer);

            return createdOrder;

        }else {
            System.out.println("customer id is not valid or customer doesn't have enough balance");
            return null;
        }

    }


    public Order placeCompoundOrder(List<Integer> simpleOrderIds){
        List<SimpleOrder> simpleOrders = new ArrayList<>();

        // get the simple order from their ids
        for(int simpleOrderId : simpleOrderIds){
            Order requestedOrder = orderRepository.getOrderById(simpleOrderId);
            if(requestedOrder != null)
                simpleOrders.add((SimpleOrder) requestedOrder);
            else{
                System.out.println("order with id " + simpleOrderId + "doesn't exist");
                return null;
            }

            List<String> placeHolders = new ArrayList<>();
            SimpleOrder order=simpleOrders.get(simpleOrderId-1);

            int id=order.getCustomerID();
            System.out.println(id);

            Customer customer = customerRepository.getCustomerByID(id);
            System.out.println(customer.getUsername());

            for(Product p : simpleOrders.get(simpleOrderId-1).getProducts()){
                placeHolders.add(p.getName());
            }

            notificationSystem.createMessage(NotificationType.ORDER_PLACEMENT, placeHolders, customer);


        }

        CompoundOrder createdCompoundOrder = new CompoundOrder(simpleOrders);
        orderRepository.addCompoundOrder(createdCompoundOrder);
        return createdCompoundOrder;
    }


    public boolean validateCustomerBalance(int customerId, double totalPrice){
        Customer customer = customerRepository.getCustomerByID(customerId);

        if(customer != null && customer.getCustomerAccount().getAccountBalance() >= totalPrice){
            customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - totalPrice);
            return true;
        }
        else
            return false;
    }
}
