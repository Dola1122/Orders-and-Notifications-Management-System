package com.example.softwareassignment2.Models;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class Order {
    private int orderID;

    private double orderPrice;

    private LocalDateTime orderTime;
}
