package com.example.softwareassignment2.Models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public abstract class NotificationTemplate {
    protected NotificationType notificationType;
    protected String subject;
    protected String content;
    protected List<String> availableLanguages;
    protected List<String> availableChannels;
    protected List<String> placeholders;

    public NotificationTemplate(){
        List<String> placeholders = new ArrayList<>();
        List<String> availableChannels = new ArrayList<>();
        List<String> availableLanguages = new ArrayList<>();
    }

}