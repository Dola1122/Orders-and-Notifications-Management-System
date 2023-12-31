package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.DTO.ShipmentResponse;
import com.example.softwareassignment2.Models.Shipment;
import com.example.softwareassignment2.Services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;
    @PostMapping
    public ShipmentResponse createShipment(@RequestBody HashMap<String, Integer> shipmentRequest) {
        Integer orderId = shipmentRequest.get("orderId");
        System.out.println(orderId);
        return shipmentService.createShipment(orderId);
    }
}
