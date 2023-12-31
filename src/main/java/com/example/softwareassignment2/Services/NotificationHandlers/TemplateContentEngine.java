package com.example.softwareassignment2.Services.NotificationHandlers;

import java.util.List;


public class TemplateContentEngine {
    public String processTemplate(String template,List<String>Products){

        String customerName = Products.get(0);
        Products.remove(0);
        String productList = String.join(", ", Products);
        template = template.replace("{Customer name}", "%s").replace("{Products}", "%s");
        template = String.format(template, customerName, productList);
        return template;
    }


}