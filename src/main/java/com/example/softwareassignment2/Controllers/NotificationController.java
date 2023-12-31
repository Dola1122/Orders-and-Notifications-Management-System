package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.NotificationType;
import com.example.softwareassignment2.Services.NotificationService;
import com.example.softwareassignment2.Services.NotificationStatistics;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationStatistics notificationStatistics;

    @GetMapping("/all-notifications")
    public Object listAllNotifications() {
        if (notificationService.getAllNotifications() == null) {
            return "No notifications created yet.";
        }
        return notificationService.getAllNotifications();
    }

    @GetMapping("/email-statistics")
    public List<String> getStatisticsEmail() {
        return notificationStatistics.mostNotifiedEmail();
    }

    @GetMapping("/template-statistics")
    public NotificationType getStatisticsTemplate() {
        return notificationStatistics.mostNotificationTemplate();
    }

    @GetMapping
    public Object listCustomerNotification(@RequestParam("customerID") int customerID) {
        if (notificationService.getAllNotifications() == null) {
            return "No notifications created yet.";
        }
        return notificationService.getCustomerNotifications(customerID);
    }

}