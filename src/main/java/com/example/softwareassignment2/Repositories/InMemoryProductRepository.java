package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductBySerialNumber(String serialNumber){
        for(Product p : products){
            if(Objects.equals(p.getSerialNumber(), serialNumber)){
                return p;
            }
        }
        return null; // if
    }
}
