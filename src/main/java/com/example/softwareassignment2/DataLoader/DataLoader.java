package com.example.softwareassignment2.DataLoader;

import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private ProductRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        // Add some dummy products to the in-memory database
        repository.addProduct(new Product("SN001", "Product1", "Vendor1", "Category1", 50.0, 10));
        repository.addProduct(new Product("SN002", "Product2", "Vendor2", "Category1", 75.0, 20));
        repository.addProduct(new Product("SN003", "Product3", "Vendor3", "Category2", 30.0, 15));
    }
}
