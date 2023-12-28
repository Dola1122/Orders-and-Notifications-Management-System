package com.example.softwareassignment2.Models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class Customer {
    private int customerID;
    private CustomerAccount customerAccount;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;


}
