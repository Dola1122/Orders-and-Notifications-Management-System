package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Models.NotificationType;
import com.example.softwareassignment2.Services.NotificationService;
import com.example.softwareassignment2.Services.NotificationStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationStatistics notificationStatistics;
    @GetMapping("/allNotifications")
    public Queue<Notification> listAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/EmailStatictics")
    public List<String>getStaticticsEmail(){
        return notificationStatistics.mostNotifiedEmail();
    }
    @GetMapping("/TemplateStatictics")
    public NotificationType getStaticticsTemplate(){
        return notificationStatistics.mostNotificationTemplate();
    }


}