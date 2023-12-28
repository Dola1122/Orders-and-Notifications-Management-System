package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer getCustomerByID(int id);
}
