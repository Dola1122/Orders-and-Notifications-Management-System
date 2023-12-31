package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationOrderShipmentTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;

import java.util.List;

public class NotificationOrderShipmentFactory extends NotificationTemplateFactory {

    @Override
    public NotificationTemplate createTemplate() {
        notificationTemplate = new NotificationOrderShipmentTemplate();
        return notificationTemplate;
    }
    @Override
    public  String processTemplate(String template, List<String> order){
        String customerName = order.get(0);
        order.remove(0);
        String productList = order.get(0);
        template = template.replace("{Customer Name}", "%s").replace("{OrderID}", "%s");
        template = String.format(template, customerName, productList);
        return template;
    }



}