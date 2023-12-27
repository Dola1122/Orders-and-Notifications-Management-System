package com.example.softwareassignment2.Models;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NotificationTemplate {
    private NotificationType notificationType;
    private String subject;
    private String content;
    private List<String> availableLanguages;
    private List<String> availableChannels;
    private List<String> placeholders;
}
