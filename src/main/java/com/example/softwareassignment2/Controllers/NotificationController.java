package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
public class NotificationController {

    private NotificationService notificationService;

    @GetMapping("/notifications")
    public Queue<Notification> listAllNotifications(){

        return null;
    }

    @GetMapping("{customerID}/notifications")
    public Queue<Notification> listCustomerNotification(@PathVariable("customerID") int customerID){
        return null;
    }

}
