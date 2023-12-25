package com.example.softwareassignment2.Models;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SimpleOrder extends Order{
    private List<Integer> productsSerialNumber;
    private int customerID;
    private double orderPrice;
    private String shippingAddress;
}
