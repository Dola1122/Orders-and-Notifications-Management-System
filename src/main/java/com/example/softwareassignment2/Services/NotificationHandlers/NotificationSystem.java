package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.*;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSystem {
    @Autowired
    private NotificationRepository notificationRepository;
    private final NotificationTemplateManager notificationTemplateManager;
    private final TemplateContentEngine templateContentEngine;

    public NotificationSystem(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
        notificationTemplateManager = new NotificationTemplateManager();
        templateContentEngine = new TemplateContentEngine();
    }

    private Notification createMessage(NotificationType type, List<String> placeHolders){
        NotificationTemplate notificationTemplate = notificationTemplateManager.createTemplate(type);
        String actualContent = templateContentEngine.processTemplate(notificationTemplate.getContent(), placeHolders);

        Notification notification = new Notification();
        int id = notificationRepository.getAllNotifications().size();
        notification.setNotificationID(id + 1);
        notification.setNotificationType(type);
        notification.setSubject(notificationTemplate.getSubject());
        notification.setActualContent(actualContent);

        return notification;
    }

    public void sendMessage(NotificationType type, List<String> placeHolders){
        Notification notification = createMessage(type, placeHolders);
        notificationRepository.saveNotification(notification);
    }
}
