package com.example.softwareassignment2.Repositories;

import com.example.softwareassignment2.Models.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
    private static final List<Customer> customers = new ArrayList<>();

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customer.setCustomerID(customers.size() + 1);
        customers.add(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public Customer getCustomerByID(int id) {
        return customers.stream()
                .filter(customer -> customer.getCustomerID() == id)
                .findFirst()
                .orElse(null);
    }

}
