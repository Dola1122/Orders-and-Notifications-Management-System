package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.softwareassignment2.Services.NotificationHandlers.NotificationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NotificationSystem notificationSystem;

    @Autowired
    private CustomerRepository customerRepository;


    public Map<String, Object> createShipment(int orderId) {
        Map<String, Object> shipmentResponse = new HashMap<>();
        Order order = orderRepository.getOrderById(orderId);

        Shipment shipment = new Shipment();


        // if the order exist in the database make the shipment
        if (order != null) {

            // check if the order has been shipped already

            if (shipmentRepository.checkIfOrderHasBeenShipped(order)) {
                System.out.println("order has been shipped already");
                shipmentResponse.put("Error", "order has been shipped already");
                return shipmentResponse;
            }

            if (!reduceShippingFeesFromCustomers(order, shipment.getShipmentFees())) {
                shipmentResponse.put("Error", "customer doesn't have enough balance to pay the shipping fees");
                return shipmentResponse;
            }
            shipment.setOrder(order);
            shipmentRepository.addShipment(shipment);
            shipmentResponse.put("shipmentDetails", shipment);
        } else {
            shipmentResponse.put("Error", "order id is not valid, order doesn't exist");
        }
        return shipmentResponse;
    }


    public boolean reduceShippingFeesFromCustomers(Order order, double shipmentFees) {

        if (order instanceof SimpleOrder) {
            int customerId = ((SimpleOrder) order).getCustomerID();
            if (checkIfUserHaveEnoughBalance(customerId, shipmentFees)) {
                reduceShippingFeesFromCustomer(customerId, shipmentFees);
            } else {
                return false;
            }
        } else if (order instanceof CompoundOrder) {
            // compound order
            // divide the fees among the customers
            List<SimpleOrder> simpleOrders = ((CompoundOrder) order).getSimpleOrders();
            double shipmentFeesDivided = shipmentFees / simpleOrders.size();
            boolean allCustomersHaveEnoughBalance = true;

            for (SimpleOrder simpleOrder : simpleOrders) {
                if (!checkIfUserHaveEnoughBalance(simpleOrder.getCustomerID(), shipmentFeesDivided)) {
                    allCustomersHaveEnoughBalance = false;
                    break;  // Break if any customer doesn't have enough balance
                }
            }

            if (allCustomersHaveEnoughBalance) {
                // If all customers have enough balance, reduce the shipping fees
                for (SimpleOrder simpleOrder : simpleOrders) {
                    reduceShippingFeesFromCustomer(simpleOrder.getCustomerID(), shipmentFeesDivided);
                    List<String> placeHolders = new ArrayList<>();
                    placeHolders.add(String.valueOf(order.getOrderID()));
                    Customer customer = customerRepository.getCustomerByID(simpleOrder.getCustomerID());
                    notificationSystem.createMessage(NotificationType.ORDER_SHIPMENT, placeHolders, customer);
                }
            } else {
                return false;  // Return false if any customer doesn't have enough balance
            }
        }
        return true;
    }

    public void reduceShippingFeesFromCustomer(int customerId, double shipmentFees) {
        Customer customer = customerRepository.getCustomerByID(customerId);
        customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - shipmentFees);

    }

    private boolean checkIfUserHaveEnoughBalance(int customerId, double shippingFees) {
        Customer customer = customerRepository.getCustomerByID(customerId);
        return customer.getCustomerAccount().getAccountBalance() >= shippingFees;
    }

    public boolean cancelShipment(int shipmentId) {
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);

        if (shipment != null) {
            LocalDateTime shipmentTime = shipment.getShipmentTime();
            LocalDateTime currentTime = LocalDateTime.now();

            // Calculate the time difference in hours
            long hoursDifference = Duration.between(shipmentTime, currentTime).toHours();

            // Check if the shipment was created less than 1 hour ago
            if (hoursDifference <= 1) {
                Order order = shipment.getOrder();

                if (order instanceof SimpleOrder) {
                    // For a SimpleOrder
                    cancelSimpleOrderShipment((SimpleOrder) order, shipmentId);
                } else if (order instanceof CompoundOrder) {
                    // For a CompoundOrder, cancel shipment for each SimpleOrder within it
                    List<SimpleOrder> simpleOrders = ((CompoundOrder) order).getSimpleOrders();
                    for (SimpleOrder simpleOrder : simpleOrders) {
                        cancelSimpleOrderShipment(simpleOrder, shipmentId);
                    }
                }

                return true;
            }
            return false;
        } else {
            System.out.println("Shipment with ID " + shipmentId + " doesn't exist");
            return false;
        }
    }

    private void cancelSimpleOrderShipment(SimpleOrder simpleOrder, int shipmentId) {
        // add the price of the shipment to the customer balance
        Customer customer = customerRepository.getCustomerByID(simpleOrder.getCustomerID());
        customer.getCustomerAccount().setAccountBalance(
                customer.getCustomerAccount().getAccountBalance() + shipmentRepository.getShipmentById(shipmentId).getShipmentFees());

        // Remove the shipment from the database
        shipmentRepository.cancelShipment(shipmentId);
    }

    public void removeAssociatedShipment(int orderId) {

        shipmentRepository.removeAssociatedShipment(orderId);
    }
}
