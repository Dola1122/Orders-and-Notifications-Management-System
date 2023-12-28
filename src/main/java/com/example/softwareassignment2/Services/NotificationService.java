package com.example.softwareassignment2.Services;

import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Queue<Notification> getAllNotifications(){
        return notificationRepository.getAllNotifications();
    }

}
