package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import com.example.softwareassignment2.Repositories.SentNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSystem {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SentNotifications sentNotifications;
    private final NotificationTemplateManager notificationTemplateManager;
    private final TemplateContentEngine templateContentEngine;

    public NotificationSystem(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        notificationTemplateManager = new NotificationTemplateManager();
        templateContentEngine = new TemplateContentEngine();
    }

    public Notification createMessage(NotificationType type, List<String> placeHolders, Customer customer) {
        placeHolders.add(0, customer.getUsername());
        NotificationTemplate notificationTemplate = notificationTemplateManager.createTemplate(type);
        String actualContent = templateContentEngine.processTemplate(notificationTemplate.getContent(), placeHolders);
        int id;
        Notification notification = new Notification();
        if (notificationRepository.getAllNotifications() == null) {
            id = 1;
        }
        id = notificationRepository.getAllNotifications().size();
        notification.setNotificationID(id + 1);
        notification.setNotificationType(type);
        notification.setSubject(notificationTemplate.getSubject());
        notification.setActualContent(actualContent);
        notification.setCustomerID(customer.getCustomerID());

        notificationRepository.saveNotification(notification);

        return notification;
    }
}