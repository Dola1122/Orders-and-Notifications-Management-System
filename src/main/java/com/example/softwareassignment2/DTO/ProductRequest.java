package com.example.softwareassignment2.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProductRequest {
    private String serialNumber;
    private int quantity;
}
