package com.example.softwareassignment2.Services;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.Notification;
import com.example.softwareassignment2.Models.NotificationType;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.NotificationRepository;
import com.example.softwareassignment2.Repositories.SentNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class NotificationStatistics {
    @Autowired
    private SentNotifications sentNotifications;
    @Autowired
    private CustomerRepository customerRepository;

    public List<String> mostNotifiedEmail() {
        if (sentNotifications.getCustomerNotification() == null)
            return null;
        Map<Integer, Queue<Notification>> customerNotifications = new HashMap<>();
        customerNotifications = sentNotifications.getCustomerNotification();
        int max = 0;
        List<Integer> customerID = new ArrayList<>();
        List<String> customerEmail = new ArrayList<>();
        for (Map.Entry<Integer, Queue<Notification>> entry : customerNotifications.entrySet()) {
            if (max > entry.getValue().size())
                continue;
            else {
                if (max != entry.getValue().size()) customerID.clear();
                max = entry.getValue().size();
                customerID.add(entry.getKey());
            }
        }

        for (Integer integer : customerID) {
            Customer customer = customerRepository.getCustomerByID(integer);
            customerEmail.add(customer.getEmail());
        }

        return customerEmail;
    }

    public NotificationType mostNotificationTemplate() {
        Map<NotificationType, Integer> typeCount = new HashMap<>();

        for (Notification notification : sentNotifications.getSentNotification()) {
            NotificationType type = notification.getNotificationType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
        }

        NotificationType mostFrequentType = null;
        int maxCount = 0;

        for (Map.Entry<NotificationType, Integer> entry : typeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentType = entry.getKey();
            }
        }
        return mostFrequentType;
    }


}