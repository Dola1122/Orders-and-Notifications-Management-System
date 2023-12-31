package com.example.softwareassignment2.DTO;

import com.example.softwareassignment2.Models.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class OrderResponse {
    private Order orderDetails;
    private String Error = "";

}
