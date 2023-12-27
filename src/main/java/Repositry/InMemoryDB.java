package Repositry;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDB implements DataBase{

    @Getter
    private Map<Integer,List<Integer>>CustomerOrders;
    private Map<Integer,List<Notification>>CustomerNotifications;
    @Getter
    private Map<Integer,List<Integer>>OrderProducts;

    @Override
    public void saveOrder(int orderID,int serialNum) {
        List<Integer> Orders=new ArrayList<>();
        if (OrderProducts.containsKey(orderID)) {
            Orders = OrderProducts.get(orderID);
            Orders.add(serialNum);
        } else {
            Orders.add(serialNum);
            OrderProducts.put(orderID, Orders);
        }
    }

    @Override
    public void saveCustomer(int CustomerID,int orderID) {
        List<Integer> Orders=new ArrayList<>();
        if (CustomerOrders.containsKey(CustomerID)) {
            Orders = CustomerOrders.get(CustomerID);
            Orders.add(orderID);
        } else {
            Orders.add(orderID);
            CustomerOrders.put(CustomerID, Orders);
        }
    }
    @Override
    public void saveNotification(int CustomerID, Notification notification) {
        List<Notification> Orders=new ArrayList<>();
        if (CustomerNotifications.containsKey(CustomerID)) {
            Orders = CustomerNotifications.get(CustomerID);
            Orders.add(notification);
        } else {
            Orders.add(notification);
            CustomerNotifications.put(CustomerID, Orders);
        }
    }

}
