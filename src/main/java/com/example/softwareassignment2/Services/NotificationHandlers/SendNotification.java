package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import com.example.softwareassignment2.Repositories.SentNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Timer;

import java.util.TimerTask;
@Component
public class SendNotification{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SentNotifications sentNotifications;

    @Scheduled(fixedRate = 60000) // 1 minute
    public void processQueue() {
        if(notificationRepository.getAllNotifications().size() == 0){
            return;
        }
        Notification notification = notificationRepository.getAllNotifications().poll();
        int id = notification.getCustomerID();
        Customer customer = customerRepository.getCustomerByID(id);
        sentNotifications.sendNotification(notification, customer);
    }


}
