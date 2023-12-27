package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.NotificationOrderPlacementTemplate;
import com.example.softwareassignment2.Models.NotificationOrderShipmentTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;
import com.example.softwareassignment2.Models.NotificationType;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationTemplateManager {
    private final Map<NotificationType, NotificationTemplate> notificationTemplates;
    public NotificationTemplateManager(){
        this.notificationTemplates = new HashMap<>();
        int typesSize = NotificationType.values().length;

        for(int i = 0; i < typesSize; i++){
            notificationTemplates.put(NotificationType.values()[i],new NotificationTemplate());
        }
//violate open close principles(Factory method)
        for (Map.Entry<NotificationType, NotificationTemplate> entry : notificationTemplates.entrySet()) {
            if (entry.getKey() == NotificationType.ORDER_PLACEMENT){
                entry.setValue(new NotificationOrderPlacementTemplate());
            }
            else if (entry.getKey() == NotificationType.ORDER_SHIPMENT) {
                entry.setValue(new NotificationOrderShipmentTemplate());
            }
        }
    }

    public NotificationTemplate getNotificationTemplate(NotificationType type){
        return notificationTemplates.get(type);
    }
}
