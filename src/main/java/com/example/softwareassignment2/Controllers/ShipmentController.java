package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;
    @PostMapping
    public Map<String, Object> createShipment(@RequestBody HashMap<String, Integer> shipmentRequest) {
        Map<String, Object> response = new HashMap<>();
        Integer orderId = shipmentRequest.get("orderId");
        // System.out.println(orderId);

        return shipmentService.createShipment(orderId);
    }
}
