package com.example.softwareassignment2.DataLoader;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.CustomerAccount;
import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import com.example.softwareassignment2.Repositories.ProductRepository;
import com.example.softwareassignment2.Services.NotificationHandlers.NotificationTemplateManager;
import com.example.softwareassignment2.Services.NotificationHandlers.SendNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Timer;


@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private NotificationTemplateManager notificationTemplateManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SendNotification sendNotification;

    @Override
    public void run(ApplicationArguments args) {
        // Add some dummy products to the in-memory database
        repository.addProduct(new Product("SN001", "Product1", "Vendor1", "Category1", 50.0, 10));
        repository.addProduct(new Product("SN002", "Product2", "Vendor2", "Category1", 75.0, 20));
        repository.addProduct(new Product("SN003", "Product3", "Vendor3", "Category2", 30.0, 15));
        customerRepository.addCustomer(new Customer(1, new CustomerAccount(500), "mohamedHesham", "77788", "011","mo@gmail.com","giza"));
        customerRepository.addCustomer(new Customer(2, new CustomerAccount(500), "ahmedHesham", "77788", "011","ah@gmail.com","giza"));
        notificationTemplateManager = new NotificationTemplateManager();
    }
}
