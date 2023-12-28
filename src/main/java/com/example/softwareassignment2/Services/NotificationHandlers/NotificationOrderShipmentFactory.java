package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationOrderShipmentTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;

public class NotificationOrderShipmentFactory implements NotificationTemplateFactory {

    @Override
    public NotificationTemplate createTemplate() {
        return new NotificationOrderShipmentTemplate();
    }
}