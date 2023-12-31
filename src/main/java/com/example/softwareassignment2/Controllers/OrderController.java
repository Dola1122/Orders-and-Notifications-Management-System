package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.DTO.OrderRequest;
import com.example.softwareassignment2.DTO.ProductRequest;
import com.example.softwareassignment2.Models.CompoundOrder;
import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Services.OrderService;
import com.example.softwareassignment2.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // create new order (simple order)
    @PostMapping()
    public Map<String, Object> placeOrder(@RequestBody OrderRequest orderRequest) throws CloneNotSupportedException {
        Map<String, Object> orderDetails = new HashMap<>();

        Order createdOrder = orderService.placeOrder(orderRequest);

        // Check if the order was successfully created
        if (createdOrder != null) {
            orderDetails.put("orderDetails", createdOrder);
            return orderDetails;
        } else {
            // error part
            String errorMessage = "Failed to place order. Please check customer ID, balance, and product availability.";
            orderDetails.put("error", errorMessage);
            return orderDetails;
        }
    }

    // compound order controller
    @PostMapping("/compound")
    public HashMap<String, Order> placeCompoundOrder(@RequestBody List<Integer> orderIds) {
        HashMap<String, Order> orderDetails = new HashMap<>();
        Order createdCompoundOrder = orderService.placeCompoundOrder(orderIds);
        if (createdCompoundOrder != null) {
            orderDetails.put("orderDetails", createdCompoundOrder);
        } else {
            orderDetails.put("couldn't place the compound order: order id doesn't exist", null);
        }
        return orderDetails;
    }


    // list all orders
    @GetMapping()
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        // no orders yet
        if (orders == null) {
            System.out.println("No orders yet");
            return null;
        } else {
            return orderService.getAllOrders();
        }
    }

    // bonus cancel order

    @PostMapping("/cancel")
    public ResponseEntity<Map<String, Object>> cancelOrder(@RequestBody Map<String, Integer> requestBody) {
        int orderId = requestBody.get("orderId");

        Map<String, Object> cancellationDetails = new HashMap<>();
        boolean isCancelled = orderService.cancelOrder(orderId);

        if (isCancelled) {
            cancellationDetails.put("message", "Order with ID " + orderId + " has been canceled successfully.");
            return new ResponseEntity<>(cancellationDetails, HttpStatus.OK);
        } else {
            cancellationDetails.put("error", "Failed to cancel the order with ID " + orderId);
            return new ResponseEntity<>(cancellationDetails, HttpStatus.BAD_REQUEST);
        }
    }


}
