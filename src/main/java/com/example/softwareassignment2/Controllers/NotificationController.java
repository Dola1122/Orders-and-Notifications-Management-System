package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/allNotifications")
    public Queue<Notification> listAllNotifications(){
            return notificationService.getAllNotifications();
    }
}
