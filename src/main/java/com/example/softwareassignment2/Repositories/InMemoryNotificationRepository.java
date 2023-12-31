package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryNotificationRepository implements NotificationRepository {
    private static final Queue<Notification> allNotifications = new LinkedList<>();



    private Map<Integer,Queue<Notification>> customerNotification = new HashMap<>();

    @Override
    public void saveNotification(Notification notification, Customer customer) {

        allNotifications.add(notification);
        Queue<Notification> notifications = new LinkedList<>();
        if (customerNotification.containsKey(customer.getCustomerID())) {
            notifications = customerNotification.get(customer.getCustomerID());
            notifications.add(notification);
        } else {
            notifications.add(notification);
            customerNotification.put(customer.getCustomerID(), notifications);
        }

    }

    @Override
    public Queue<Notification> getAllNotifications() {
        return allNotifications;
    }
    public Map<Integer, Queue<Notification>> getCustomerNotification() {
        return customerNotification;
    }


}