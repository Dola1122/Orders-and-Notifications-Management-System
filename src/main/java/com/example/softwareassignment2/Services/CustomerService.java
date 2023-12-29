package com.example.softwareassignment2.Services;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.getAllCustomers();
    }

    public boolean addCustomer(Customer customer) {
        if (!isEmailOrUsernameDuplicate(customer.getEmail(), customer.getUsername())) {
            repository.addCustomer(customer);
            return true;
        } else
            return false;
    }

    public boolean isEmailOrUsernameDuplicate(String email, String username) {
        // Check if email or username already exists
        return repository.getAllCustomers().stream()
                .anyMatch(c -> c.getEmail().equals(email) || c.getUsername().equals(username));
    }

    public boolean login(String email, String password) {
        Customer customer = repository.getCustomerByEmail(email);

        if (customer != null && customer.getPassword().equals(password)) {
            return true; // Login successful
        }

        return false; // Invalid credentials
    }


}
