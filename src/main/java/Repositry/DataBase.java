package Repositry;

import com.example.softwareassignment2.Models.Notification;

import java.util.List;

public interface DataBase {
    //save
    void saveOrder(int orderID,int serialNum);
    void saveCustomer(int CustomerID, int orderID);

    void saveNotification(int CustomerID, Notification notification);
}
