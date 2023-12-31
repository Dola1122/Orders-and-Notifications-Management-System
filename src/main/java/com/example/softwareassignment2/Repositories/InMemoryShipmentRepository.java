package com.example.softwareassignment2.Repositories;



import com.example.softwareassignment2.Models.Order;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.Shipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository

public class InMemoryShipmentRepository implements ShipmentRepository {
    private static final List<Shipment> shipmentsDB = new ArrayList<>();


    public void addShipment(Shipment shipment){
        shipment.setId(shipmentsDB.size() + 1);
        shipmentsDB.add(shipment);
    }

    public List<Shipment> getAllShipments(){
        return shipmentsDB;
    }



    public Shipment getShipmentById(int id){
        for(Shipment shipment: shipmentsDB){
            if(shipment.getId() == id)
                return shipment;
        }
        return null;
    }


    public boolean checkIfOrderHasBeenShipped(Order order){
        for (Shipment shipment : shipmentsDB){
            if(Objects.equals(order, shipment.getOrder())){
                return true;
            }
        }
        return false;
    }

}
