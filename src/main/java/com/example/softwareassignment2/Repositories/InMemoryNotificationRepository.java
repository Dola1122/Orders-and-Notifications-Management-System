package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Notification;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryNotificationRepository implements NotificationRepository {
    private static final Queue<Notification> allNotifications = new LinkedList<>();

    @Override
    public void saveNotification(Notification notification) {
        allNotifications.add(notification);
    }

    @Override
    public Queue<Notification> getAllNotifications() {
        return allNotifications;
    }

}
