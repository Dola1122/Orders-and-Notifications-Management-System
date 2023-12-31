package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.OrderResponse;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.CompoundOrder;
import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Services.OrderService;
import com.example.softwareassignment2.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // create new order (simple order)
    @PostMapping()
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) throws CloneNotSupportedException {
        HashMap<String, Order> orderDetails = new HashMap<>();
        for(ProductRequest pr : orderRequest.getProducts()){
            System.out.println(pr.getSerialNumber());
        }
        return orderService.placeOrder(orderRequest);
    }

    // compound order controller
    @PostMapping("/compound")
    public HashMap<String, Order> placeCompoundOrder(@RequestBody List<Integer> orderIds){
        HashMap<String, Order> orderDetails = new HashMap<>();
        Order createdCompoundOrder = orderService.placeCompoundOrder(orderIds);
        if(createdCompoundOrder != null){
            orderDetails.put("orderDetails", createdCompoundOrder);
        }else{
            orderDetails.put("couldn't place the compound order: order id doesn't exist", null);
        }
        return orderDetails;
    }


    // list all orders
    @GetMapping()
    public List<Order> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        // no orders yet
        if(orders == null){
            System.out.println("No orders yet");
            return null;
        }else{
            return orderService.getAllOrders();
        }
    }


}
