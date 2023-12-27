package com.example.softwareassignment2.Models;

import java.util.ArrayList;
import java.util.List;

public class NotificationOrderShipmentTemplate extends NotificationTemplate {
    public NotificationOrderShipmentTemplate(){
        List<String> placeholders = new ArrayList<>();
        placeholders.add("{Customer Name}");
        placeholders.add("{orderId}");

        List<String> availableChannels = new ArrayList<>();
        availableChannels.add("SMS");
        availableChannels.add("Email");

        List<String> availableLanguages = new ArrayList<>();
        availableLanguages.add("Arabic");
        availableLanguages.add("English");

        setNotificationType(NotificationType.ORDER_PLACEMENT);
        setSubject("Order Shipment");
        setContent("Dear {Customer Name}, your {Product Name} is on its way—thanks for choosing our store!");
        setPlaceholders(placeholders);
        setAvailableChannels(availableChannels);
        setAvailableLanguages(availableLanguages);
    }
}
