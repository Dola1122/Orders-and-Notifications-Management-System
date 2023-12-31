package com.example.softwareassignment2.DTO;

import com.example.softwareassignment2.Models.Shipment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ShipmentResponse {

    Shipment shipmentDetails;

    String error;

}
