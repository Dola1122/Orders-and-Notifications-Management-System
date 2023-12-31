package com.example.softwareassignment2.Models;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product implements Cloneable {
    private String serialNumber;
    private String name;
    private String vendor;
    private String category;
    private double price;
    private int quantity;


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
