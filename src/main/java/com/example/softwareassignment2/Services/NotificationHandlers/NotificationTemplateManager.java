package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationTemplate;
import com.example.softwareassignment2.Models.NotificationType;

import java.util.HashMap;
import java.util.Map;

public class NotificationTemplateManager {

    private final Map<NotificationType, NotificationTemplateFactory> notificationCreators;

    public NotificationTemplateManager() {
        this.notificationCreators = new HashMap<>();
        notificationCreators.put(NotificationType.ORDER_PLACEMENT, new NotificationOrderPlacementFactory());
        notificationCreators.put(NotificationType.ORDER_SHIPMENT, new NotificationOrderShipmentFactory());
    }

    public NotificationTemplate createTemplate(NotificationType type) {
        return notificationCreators.get(type).createTemplate();
    }
}