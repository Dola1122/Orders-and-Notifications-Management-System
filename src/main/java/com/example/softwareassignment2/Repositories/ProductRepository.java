package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Product;
import org.springframework.stereotype.Repository;
import java.util.List;



public interface ProductRepository {
    public List<Product> getAllProducts();
    public void addProduct(Product product);

    Product getProductBySerialNumber(String serialNumber);

}


