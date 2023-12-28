package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSystem {
    @Autowired
//    private final NotificationQueue notificationQueue;
    private final NotificationTemplateManager notificationTemplateManager;
    private final TemplateContentEngine templateContentEngine;
    private Notification notification;

    public NotificationSystem(){
//        notificationQueue = new NotificationQueue();
        notificationTemplateManager = new NotificationTemplateManager();
        templateContentEngine = new TemplateContentEngine();
    }

    public void CreateMessage(NotificationType type){
        NotificationTemplate notificationTemplate = notificationTemplateManager.createTemplate(type);
        String actualContent = templateContentEngine.processTemplate(notificationTemplate.getContent(), notificationTemplate.getPlaceholders());
        notification.setActualContent(actualContent);
    }

    public void sendMessage(){
//        notificationQueue.enqueueNotification(notification);
    }
}
