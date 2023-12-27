package com.example.softwareassignment2.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class Order {
    private int orderID;

    private double orderPrice;


}
