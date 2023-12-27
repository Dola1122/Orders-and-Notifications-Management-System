package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.SimpleOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private static final List<Order> orders = new ArrayList<>();
    @Override
    public Order addOrder(List<Product> products, int customerID) {
        SimpleOrder newOrder = new SimpleOrder();
        List<Product> orderProducts = new ArrayList<>();
        for(Product p : products){
            orderProducts.add(p);
        }
        newOrder.setOrderID(orders.size() + 1);
        newOrder.setProducts(orderProducts);
        newOrder.setCustomerID(customerID);
        orders.add(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }


}
