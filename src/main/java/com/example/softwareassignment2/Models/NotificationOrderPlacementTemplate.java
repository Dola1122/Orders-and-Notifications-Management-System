package com.example.softwareassignment2.Models;

public class NotificationOrderPlacementTemplate extends NotificationTemplate {

    public NotificationOrderPlacementTemplate(){
        super();
        setNotificationType(NotificationType.ORDER_PLACEMENT);
        setSubject("Order Placement");

        placeholders.add("{Customer name}");
        placeholders.add("{Products}");

        availableChannels.add("SMS");
        availableChannels.add("Email");

        availableLanguages.add("Arabic");
        availableLanguages.add("English");

        setContent(" Dear {Customer name} , your booking of the {Products} is confirmed. thanks for using our store :) ");

    }

}
