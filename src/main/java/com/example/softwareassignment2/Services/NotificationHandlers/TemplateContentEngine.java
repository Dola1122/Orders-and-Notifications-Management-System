package com.example.softwareassignment2.Services.NotificationHandlers;

import java.util.List;


public class TemplateContentEngine {
    public String processTemplate(String template,List<String>Products){
        template = template.replace("{x}", Products.get(0));
        Products.remove(Products.get(0));
        String productList = String.join(", ", Products);
        template = template.replace("{y}", productList);
        return template;
    }

}
