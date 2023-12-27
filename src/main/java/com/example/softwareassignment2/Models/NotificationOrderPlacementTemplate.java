package com.example.softwareassignment2.Models;

import java.util.ArrayList;
import java.util.List;

public class NotificationOrderPlacementTemplate extends NotificationTemplate {
    public NotificationOrderPlacementTemplate(){
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
        setSubject("Order Placement");
        setContent("");
        setPlaceholders(placeholders);
        setAvailableChannels(availableChannels);
        setAvailableLanguages(availableLanguages);
    }
}
