package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface SentNotifications {

    public void sendNotification(Notification notification, Customer customer);

    public List<Notification> getSentNotification();
    public Map<Integer, Queue<Notification>> getCustomerNotification();
    public Queue<Notification> getCustomerNotifications(int customerID);
}
