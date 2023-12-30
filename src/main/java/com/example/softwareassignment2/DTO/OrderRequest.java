package com.example.softwareassignment2.DTO;

import com.example.softwareassignment2.Models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class OrderRequest {
    private List<ProductRequest> products;
    private int customerID;
    private String shippingAddress;

    // getters and setters
}
