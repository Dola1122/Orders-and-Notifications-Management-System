package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Shipment;

import java.util.List;

public interface ShipmentRepository {
    public void addShipment(Shipment shipment);

    public List<Shipment> getAllShipments();



    public Shipment getShipmentById(int id);
    public boolean checkIfOrderHasBeenShipped(Order order);
}
