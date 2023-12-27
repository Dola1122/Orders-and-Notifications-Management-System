package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Notification;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;


public class NotificationQueue {
    private final Queue<Notification> notifications;
    public NotificationQueue(){
        this.notifications = new LinkedList<>();
    }

    public void enqueueNotification(Notification notification){
        notifications.add(notification);
    }

    public Notification dequeueNotification(){
        return notifications.poll();
    }

    public Queue<Notification> listNotificationQueue(){
        return notifications;
    }

}
