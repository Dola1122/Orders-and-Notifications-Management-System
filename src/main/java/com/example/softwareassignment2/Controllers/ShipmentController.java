package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/cancel")
    public ResponseEntity<Map<String, Object>> cancelShipment(@RequestBody Map<String, Integer> requestBody) {
        int shipmentId = requestBody.get("shipmentId");

        Map<String, Object> cancellationDetails = new HashMap<>();
        boolean isCancelled = shipmentService.cancelShipment(shipmentId);

        if (isCancelled) {
            cancellationDetails.put("message", "Shipment with ID " + shipmentId + " has been canceled successfully.");
            return new ResponseEntity<>(cancellationDetails, HttpStatus.OK);
        } else {
            cancellationDetails.put("error", "Failed to cancel the shipment with ID " + shipmentId);
            return new ResponseEntity<>(cancellationDetails, HttpStatus.BAD_REQUEST);
        }
    }

}
