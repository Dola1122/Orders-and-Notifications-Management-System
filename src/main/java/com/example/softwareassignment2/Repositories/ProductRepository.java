package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface ProductRepository {
    public List<Product> getAllProducts();

    public void addProduct(Product product);

    public Map<String, Integer> getAllCategories();

    Product getProductBySerialNumber(String serialNumber);

}


