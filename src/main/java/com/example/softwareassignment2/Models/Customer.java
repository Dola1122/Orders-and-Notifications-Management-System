package com.example.softwareassignment2.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    private int customerID;
    private CustomerAccount customerAccount;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
}
