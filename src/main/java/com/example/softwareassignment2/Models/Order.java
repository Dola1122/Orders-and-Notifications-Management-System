package com.example.softwareassignment2.Models;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    private int orderID;
    private Customer customer;
    private List<Product> product;
}
//repo list of product

