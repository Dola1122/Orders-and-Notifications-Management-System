package com.example.softwareassignment2.Controllers;

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
    public HashMap<String, Shipment> createShipment(@RequestBody HashMap<String, Integer> shipmentRequest) {
        Integer orderId = shipmentRequest.get("orderId");
        System.out.println(orderId);
        Shipment shipment = shipmentService.createShipment(orderId);
        HashMap<String, Shipment> shipmentDetails = new HashMap<>();
        if(shipment != null){
            shipmentDetails.put("shipmentDetails",shipment);
        }else {
            System.out.println("One or more customers don't have enough balance for the shipment or Order doesn't exist");
            shipmentDetails.put("One or more customers don't have enough balance for the shipment or Order doesn't exist",null);
        }
        return shipmentDetails;

    }
}
