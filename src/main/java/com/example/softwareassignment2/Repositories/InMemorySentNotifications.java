package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemorySentNotifications implements SentNotifications{
    private final List<Notification> allSentNotifications = new ArrayList<>();
    private final Map<Integer, Queue<Notification>> customerNotification = new HashMap<>();

    @Override
    public void sendNotification(Notification notification, Customer customer) {
        allSentNotifications.add(notification);
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
    public List<Notification> getSentNotification() {
        if(allSentNotifications.size()==0){
            return null;
        }
        return allSentNotifications;
    }

    @Override
    public Map<Integer, Queue<Notification>> getCustomerNotification() {
        return null;
    }

    @Override
    public Queue<Notification> getCustomerNotifications(int customerID) {
        if(customerNotification.containsKey(customerID)){
            return customerNotification.get(customerID);
        }
        return null;
    }
}
