package com.example.softwareassignment2.Services.NotificationHandlers;

import com.example.softwareassignment2.Models.NotificationOrderPlacementTemplate;
import com.example.softwareassignment2.Models.NotificationTemplate;

import java.util.List;

public class NotificationOrderPlacementFactory extends NotificationTemplateFactory {
    @Override
    public NotificationTemplate createTemplate() {
        notificationTemplate = new NotificationOrderPlacementTemplate();
        return notificationTemplate;
    }
    @Override
    public  String processTemplate(String template, List<String> order){
        String customerName = order.get(0);
        order.remove(0);
        String productList = String.join(", ", order);
        template = template.replace("{Customer name}", "%s").replace("{Products}", "%s");
        template = String.format(template, customerName, productList);
        return template;
    }
}