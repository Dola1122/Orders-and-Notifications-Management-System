package com.example.softwareassignment2.Services;

import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import com.example.softwareassignment2.Repositories.SentNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private SentNotifications sentNotifications;

    public Queue<Notification> getAllNotifications(){
        System.out.println(notificationRepository.getAllNotifications().size());
        return notificationRepository.getAllNotifications();
    }

    public Queue<Notification> getCustomerNotifications(int customerId){

        return sentNotifications.getCustomerNotifications(customerId);
    }

}
