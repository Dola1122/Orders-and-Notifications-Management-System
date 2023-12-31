package com.example.softwareassignment2.Models;

public class NotificationOrderShipmentTemplate extends NotificationTemplate {

    public NotificationOrderShipmentTemplate(){
        super();
        setNotificationType(NotificationType.ORDER_SHIPMENT);
        setSubject("Order Shipment");

        placeholders.add("{Customer Name}");
        placeholders.add("{orderId}");

        availableChannels.add("SMS");
        availableChannels.add("Email");

        availableLanguages.add("Arabic");
        availableLanguages.add("English");

        setContent("Dear {Customer Name}, your order id : {OrderID} is on its way—thanks for choosing our store!");
    }
}
