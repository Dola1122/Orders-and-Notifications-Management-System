package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationTemplate;

import java.util.List;

public abstract class NotificationTemplateFactory {
    protected NotificationTemplate notificationTemplate;

    public abstract NotificationTemplate createTemplate();

    public abstract String processTemplate(String template, List<String> order);
}
