package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;

import java.util.Map;
import java.util.Queue;

public interface NotificationRepository {
    public Queue<Notification> getAllNotifications();
    public void saveNotification(Notification notification, Customer customer);
    public Map<Integer, Queue<Notification>> getCustomerNotification();
}