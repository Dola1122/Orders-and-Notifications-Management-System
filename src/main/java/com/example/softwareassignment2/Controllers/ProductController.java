package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Product;
import com.example.softwareassignment2.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Map<String, List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return Collections.singletonMap("products", products);
    }


}
