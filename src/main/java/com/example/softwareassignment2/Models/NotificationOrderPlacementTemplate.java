package com.example.softwareassignment2.Models;

import java.util.ArrayList;
import java.util.List;

public class NotificationOrderPlacementTemplate extends NotificationTemplate {
    public NotificationOrderPlacementTemplate(){
        List<String> placeholders = new ArrayList<>();
        placeholders.add("{Customer name}");
        placeholders.add("{Products}");

        List<String> availableChannels = new ArrayList<>();
        availableChannels.add("SMS");
        availableChannels.add("Email");


        List<String> availableLanguages = new ArrayList<>();
        availableLanguages.add("Arabic");
        availableLanguages.add("English");


        setNotificationType(NotificationType.ORDER_PLACEMENT);
        setSubject("Order Placement");
        setContent(" Dear {Customer name} , your booking of the {Products} is confirmed. thanks for using our store :) ");
        setPlaceholders(placeholders);
        setAvailableChannels(availableChannels);
        setAvailableLanguages(availableLanguages);
    }
}
