package com.example.softwareassignment2.Services;


import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.SimpleOrder;
import com.example.softwareassignment2.Repositories.OrderRepository;
import com.example.softwareassignment2.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;

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

        // make and order with sent products and customer id
        Order createdOrder = orderRepository.addOrder(products, customerID);

        createdOrder.setOrderPrice(totalOrderPrice);
        return createdOrder;
    }
}
