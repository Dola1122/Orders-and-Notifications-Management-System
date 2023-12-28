package com.example.softwareassignment2.Controllers;

import com.example.softwareassignment2.Models.Customer;
import com.example.softwareassignment2.Models.CustomerAccount;
import com.example.softwareassignment2.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Map<String, List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Collections.singletonMap("customers", customers);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> customerData) {
        Customer customer = createCustomerFromMap(customerData);

        if (isValidCustomer(customer)) {
            if (customerService.addCustomer(customer)) {
                return ResponseEntity.ok("Registration successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email or username");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }
    }


    private Customer createCustomerFromMap(Map<String, Object> customerData) {
        Customer customer = new Customer();
        CustomerAccount customerAccount = new CustomerAccount();

        customerAccount.setAccountBalance((Double) customerData.get("accountBalance"));
        customer.setCustomerAccount(customerAccount);
        customer.setUsername((String) customerData.get("username"));
        customer.setPassword((String) customerData.get("password"));
        customer.setPhoneNumber((String) customerData.get("phoneNumber"));
        customer.setEmail((String) customerData.get("email"));
        customer.setAddress((String) customerData.get("address"));

        return customer;
    }

    private boolean isValidCustomer(Customer customer) {
        return customer != null &&
                customer.getCustomerAccount() != null &&
                customer.getUsername() != null &&
                customer.getPassword() != null &&
                customer.getPhoneNumber() != null &&
                customer.getEmail() != null &&
                customer.getAddress() != null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        // make sure that email and password are provided in the body
        if (email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        boolean loginSuccessful = customerService.login(email, password);

        if (loginSuccessful) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
