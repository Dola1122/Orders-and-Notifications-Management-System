package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationOrderShipmentTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;

public class NotificationOrderShipmentFactory extends NotificationTemplateFactory {

    @Override
    public NotificationTemplate createTemplate() {
        notificationTemplate = new NotificationOrderShipmentTemplate();
        return notificationTemplate;
    }
}