package com.example.softwareassignment2.Repositories;


import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.Shipment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository

public class InMemoryShipmentRepository implements ShipmentRepository {
    private static final List<Shipment> shipmentsDB = new ArrayList<>();


    public void addShipment(Shipment shipment) {
        shipment.setId(shipmentsDB.size() + 1);
        shipment.setShipmentTime(LocalDateTime.now());
        shipmentsDB.add(shipment);
    }

    public List<Shipment> getAllShipments() {
        return shipmentsDB;
    }


    public Shipment getShipmentById(int id) {
        for (Shipment shipment : shipmentsDB) {
            if (shipment.getId() == id)
                return shipment;
        }
        return null;
    }

    @Override
    public void cancelShipment(Integer shipmentId) {
        Shipment shipmentToCancel = getShipmentById(shipmentId);
        if (shipmentToCancel != null) {
            shipmentsDB.remove(shipmentToCancel);
        } else {
            System.out.println("Shipment with ID " + shipmentId + " not found.");
        }
    }


    @Override
    public void removeAssociatedShipment(Integer orderId) {
        Shipment associatedShipment = findAssociatedShipment(orderId);

        if (associatedShipment != null) {
            shipmentsDB.remove(associatedShipment);
            System.out.println("Associated Shipment with ID " + associatedShipment.getId() + " has been removed.");
        } else {
            System.out.println("No associated shipment found for Order with ID " + orderId);
        }
    }

    private Shipment findAssociatedShipment(Integer orderId) {
        for (Shipment shipment : shipmentsDB) {
            if (orderId.equals(shipment.getOrder().getOrderID())) {
                return shipment;
            }
        }
        return null;
    }


}
