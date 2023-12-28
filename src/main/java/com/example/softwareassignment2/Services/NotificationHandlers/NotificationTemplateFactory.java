package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationTemplate;

public abstract class NotificationTemplateFactory {
    protected NotificationTemplate notificationTemplate;

    public abstract NotificationTemplate createTemplate();
}
