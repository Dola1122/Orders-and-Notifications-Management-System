package com.example.softwareassignment2.Controllers;

import java.util.List;


public class TemplateContentEngine {
    public String processTemplate(String template,List<String>Products){
        template = template.replace("{x}", Products.get(0));
        Products.remove(Products.get(0));
        String productList = String.join(", ", Products);
        template = template.replace("{y}", productList);
        return template;
    }

//
//        for (Map.Entry<String, String> entry : values.entrySet()) {
//            String placeholder = entry.getKey();
//            String actualValue = entry.getValue();
//            template = template.replace("{" + placeholder + "}", actualValue);
//
//        }
//
//        return template;
//    }
}
