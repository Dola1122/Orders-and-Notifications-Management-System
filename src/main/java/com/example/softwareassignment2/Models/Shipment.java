package com.example.softwareassignment2.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Shipment {

    private int  id;

    private Order order;
    private ShipmentStatus status; // enum that indicates the status of the shipment (pending, delivered, cancelled)

    private double shipmentFees  = 25; // predefined
}
