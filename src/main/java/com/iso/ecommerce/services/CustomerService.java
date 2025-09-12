package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.CustomerDAO;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.util.PasswordUtil;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void save(String firstName, String lastName, String email, String password){
        Customer customer = new Customer(firstName,lastName,email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Registration successful!");
    }
}
