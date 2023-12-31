package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.DTO.ShipmentResponse;
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


    public ShipmentResponse createShipment(int orderId) {
        ShipmentResponse shipmentResponse = new ShipmentResponse();
        shipmentResponse.setShipmentDetails(null);
        Order order = orderRepository.getOrderById(orderId);

        Shipment shipment = new Shipment();


        // if the order exist in the database make the shipment
        if(order != null){
            if(!reduceShippingFeesFromCustomers(order, shipment.getShipmentFees())){
                shipmentResponse.setError("customer doesn't have enough balance to pay the shipping fees");
                return  shipmentResponse;
            }
            shipment.setOrder(order);
            shipment.setStatus(ShipmentStatus.PENDING);
            shipmentRepository.addShipment(shipment);
            shipmentResponse.setShipmentDetails(shipment);
        }
        else{
            shipmentResponse.setError("order id is not valid, order doesn't exist");
        }
        return shipmentResponse;
    }


    public boolean reduceShippingFeesFromCustomers(Order order, double shipmentFees){

        if(order instanceof SimpleOrder){
            System.out.println(((SimpleOrder) order).getCustomerID());
            int customerId = ((SimpleOrder) order).getCustomerID();
            if(checkIfUserHaveEnoughBalance(customerId, shipmentFees)){
                reduceShippingFeesFromCustomer(customerId, shipmentFees);
            }else {
                return false;
            }
        }else{
            // compound order
            // divide the fees among the customers
            List<SimpleOrder> simpleOrders = ((CompoundOrder)order).getSimpleOrders();
            double shipmentFeesDivided = shipmentFees / simpleOrders.size();
            for(SimpleOrder simpleOrder : simpleOrders){
                if(checkIfUserHaveEnoughBalance(simpleOrder.getCustomerID(), shipmentFeesDivided)){
                    return false;
                }
            }
            // if they all have enough balance reduce the shipping fees
            for(SimpleOrder simpleOrder : simpleOrders){
                reduceShippingFeesFromCustomer(simpleOrder.getCustomerID(), shipmentFeesDivided);
            }
        }
        return true;
    }

    public void reduceShippingFeesFromCustomer(int customerId, double shipmentFees){
        Customer customer = customerRepository.getCustomerByID(customerId);
        customer.getCustomerAccount().setAccountBalance(customer.getCustomerAccount().getAccountBalance() - shipmentFees);
    }

    private boolean checkIfUserHaveEnoughBalance(int customerId ,double shippingFees){
        Customer customer = customerRepository.getCustomerByID(customerId);
        return customer.getCustomerAccount().getAccountBalance() >= shippingFees;
    }
}
