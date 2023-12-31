package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.CompoundOrder;
import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.SimpleOrder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private static final List<Order> orders = new ArrayList<>();

    @Override
    public Order addOrder(List<Product> products, int customerID, String shippingAddress) {
        SimpleOrder newOrder = new SimpleOrder();
        newOrder.setShippingAddress(shippingAddress);
        newOrder.setOrderID(orders.size() + 1);
        newOrder.setProducts(products);
        newOrder.setCustomerID(customerID);
        newOrder.setOrderTime(LocalDateTime.now());
        orders.add(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }


    public Order getOrderById(Integer id) {
        return orders.stream()
                .filter(order -> order.getOrderID() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCompoundOrder(CompoundOrder compoundOrder) {
        compoundOrder.setOrderID(orders.size() + 1);
        orders.add(compoundOrder);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        Order orderToCancel = getOrderById(orderId);
        if (orderToCancel != null) {
            orders.remove(orderToCancel);
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }

}
