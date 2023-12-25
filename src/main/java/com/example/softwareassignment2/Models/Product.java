package com.example.softwareassignment2.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    private int serialNumber;
    private String name;
    private String category;
    private String vendor;
    private int price;
}
