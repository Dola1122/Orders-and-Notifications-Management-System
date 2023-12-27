package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.ProductRequest;
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

    // create new order
    @PostMapping()
    public HashMap<String, Order> placeOrder(@RequestBody OrderRequest orderRequest){
        // json object should look like
        /*
            {
                "products" : [
                    {
                        "serialNumber" : "SN001",
                        "quantity" : 1
                    },
                    {
                        "serialNumber"  : "SN002",
                        "quantity" : 2
                    }

                ],
                "customerID" : 123
            }
        */
        HashMap<String, Order> orderDetails = new HashMap<>();
        for(ProductRequest pr : orderRequest.getProducts()){
            System.out.println(pr.getSerialNumber());
        }
        Order createdOrder = orderService.placeOrder(orderRequest);

        orderDetails.put("orderDetails", createdOrder);
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
