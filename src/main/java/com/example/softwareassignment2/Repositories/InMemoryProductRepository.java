package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Models.Shipment;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    public Map<String, Integer> getAllCategories() {
        Map<String, Integer> categoryCountMap = new HashMap<>();

        for (Product p : products) {
            String category = p.getCategory();

            // Update the count for the category
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
        }

        return categoryCountMap;
    }


    public boolean reduceProductsQuantity(List<Product> products){
        for(Product product : products){
            for(Product p: products){
                if(Objects.equals(p.getSerialNumber(), product.getSerialNumber())){
                    if(p.getQuantity() < product.getQuantity()){
                        return false;
                    }
                }

            }
        }

        for(Product product : products){
            for(Product p: products){
                if(Objects.equals(p.getSerialNumber(), product.getSerialNumber())) {
                    p.setQuantity(p.getQuantity() - product.getQuantity());
                }
            }
        }
        return true;
    }

    public void rollbackProductQuantities(List<Product> productsToRollback) {
        for (Product productToRollback : productsToRollback) {
            for (Product originalProduct : products) {
                if (Objects.equals(originalProduct.getSerialNumber(), productToRollback.getSerialNumber())) {
                    // Rollback quantity to the original value
                    originalProduct.setQuantity(originalProduct.getQuantity() + productToRollback.getQuantity());
                    break; // Break the inner loop once the product is found
                }
            }
        }
    }

}
