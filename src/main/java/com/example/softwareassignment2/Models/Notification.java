package com.example.softwareassignment2.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {
    private int notificationID;
    private String subject;
    private String actualContent;
}
