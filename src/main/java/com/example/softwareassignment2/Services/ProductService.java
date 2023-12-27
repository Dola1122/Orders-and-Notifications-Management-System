package com.example.softwareassignment2.Services;

import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }


    public Product getProductBySerialNumber(String serialNumber){
        // Product p;
        Product p = repository.getProductBySerialNumber(serialNumber);
        return p;
    }
}
