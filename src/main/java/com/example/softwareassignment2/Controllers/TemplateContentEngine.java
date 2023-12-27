package com.example.softwareassignment2.Controllers;

import java.util.Map;

public class TemplateContentEngine {
    public String processTemplate(String template, Map<String, String> values){
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String placeholder = entry.getKey();
            String actualValue = entry.getValue();
            template = template.replace("{" + placeholder + "}", actualValue);
        }
        return template;
    }
}
