package com.example.softwareassignment2.Models;

import lombok.*;

import java.util.Queue;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    private int customerID;//system
    private CustomerAccount customerAccount;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private Queue<String>receiveNotifiation;
}

//repo list with acees order->customer id product cstomer->customer id
// repo 3 lists produt customer order(orderid ,list product) ,map cutomer id list orderid
// repo list of notificaiton
