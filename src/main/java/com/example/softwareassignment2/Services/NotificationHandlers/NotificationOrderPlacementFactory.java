package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationOrderPlacementTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;

public class NotificationOrderPlacementFactory implements NotificationTemplateFactory {
    @Override
    public NotificationTemplate createTemplate() {
        return new NotificationOrderPlacementTemplate();
    }
}