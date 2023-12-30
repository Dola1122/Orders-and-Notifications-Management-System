package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public Shipment createShipment(int orderId) {

        Order order = orderRepository.getOrderById(orderId);

        Shipment shipment = new Shipment();


        // if the order exist in the database make the shipment
        if(order != null && reduceShippingFeesFromCustomers(order, shipment.getShipmentFees())){
            shipment.setOrder(order);
            shipment.setStatus(ShipmentStatus.PENDING);
            return shipment;
        }
        // else return null
        else{
            return null;
        }
    }


    public boolean reduceShippingFeesFromCustomers(Order order, double shipmentFees){

        if(order instanceof SimpleOrder){
            System.out.println(((SimpleOrder) order).getCustomerID());
            int customerId = ((SimpleOrder) order).getCustomerID();
            if(!reduceShippingFeesFromCustomer(customerId, shipmentFees))
                return false;
        }else{
            // compound order
            // divide the fees among the customers
            List<SimpleOrder> simpleOrders = ((CompoundOrder)order).getSimpleOrders();
            double shipmentFeesDivided = shipmentFees / simpleOrders.size();
            for(SimpleOrder simpleOrder : simpleOrders){
                if(!reduceShippingFeesFromCustomer(simpleOrder.getCustomerID(), shipmentFeesDivided)){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean reduceShippingFeesFromCustomer(int customerId, double shipmentFees){
        Customer customer = customerRepository.getCustomerByID(customerId);

        if(customer.getCustomerAccount().getAccountBalance() >= shipmentFees){
            customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - shipmentFees);
            return true;
        }else {
            return false;
        }
    }
}
