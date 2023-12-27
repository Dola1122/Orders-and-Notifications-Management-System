package com.example.softwareassignment2.Models;

import java.util.ArrayList;
import java.util.List;

public class NotificationOrderShipmentTemplate extends NotificationTemplate {
    public NotificationOrderShipmentTemplate(){
        List<String> placeholders = new ArrayList<>();
        placeholders.add("");
        placeholders.add("");
        placeholders.add("");

        List<String> availableChannels = new ArrayList<>();
        availableChannels.add("");
        availableChannels.add("");
        availableChannels.add("");

        List<String> availableLanguages = new ArrayList<>();
        availableLanguages.add("");
        availableLanguages.add("");
        availableLanguages.add("");

        setNotificationType(NotificationType.ORDER_PLACEMENT);
        setSubject("Order Shipment");
        setContent("");
        setPlaceholders(placeholders);
        setAvailableChannels(availableChannels);
        setAvailableLanguages(availableLanguages);
    }
}
