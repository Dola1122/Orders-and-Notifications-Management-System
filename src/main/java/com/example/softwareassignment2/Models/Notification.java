package com.example.softwareassignment2.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {
    private NotificationTemplate notificationTemplate;
    private int notificationID;
    private Customer customer;
    private String actualContent;
}
