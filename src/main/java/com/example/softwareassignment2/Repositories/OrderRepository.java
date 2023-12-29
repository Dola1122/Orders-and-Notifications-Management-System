package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface OrderRepository {

    public Order addOrder(List<Product> products, int customerID);
    public List<Order> getAllOrders();

    public Order getOrderById(Integer id);
}
