package com.example.softwareassignment2.Models;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompoundOrder extends Order{
    private List<SimpleOrder> simpleOrders;


}
