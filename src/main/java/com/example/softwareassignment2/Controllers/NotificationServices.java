package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.*;

import java.security.PublicKey;

public class NotificationServices {
    NotificationQueue notificationQueue;
    NotificationTemplateManager notificationTemplateManager;
    TemplateContentEngine templateContentEngine;
    Notification notification;
    NotificationTemplate t;
    String template;

    public NotificationServices(NotificationType type){
        notificationQueue =new NotificationQueue();
        notificationTemplateManager=new NotificationTemplateManager();
        templateContentEngine=new TemplateContentEngine();
    }
    public void CreateMessage(NotificationType type){
       t= notificationTemplateManager.getNotificationTemplate(type);
       template=templateContentEngine.processTemplate(t.getContent(),t.getPlaceholders());
       notification.setActualContent(template);

    }
    public void sendMessage(){
        notificationQueue.enqueueNotification(notification);
    }


}
