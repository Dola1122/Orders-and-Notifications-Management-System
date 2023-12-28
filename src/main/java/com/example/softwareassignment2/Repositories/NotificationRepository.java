package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Notification;

import java.util.Queue;

public interface NotificationRepository {
    public Queue<Notification> getAllNotifications();
    public void saveNotification(Notification notification);
}
