package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.CustomerDAO;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.util.PasswordUtil;

public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String firstName, String lastName, String email, String password) throws ISOStoreException {

        boolean isExist = customerDAO.existByEmail(email);
        if (isExist) {
            throw new ISOStoreException(ExceptionMessages.CUSTOMER_EMAIL_ALREADY_EXITS);
        }
        Customer customer = new Customer(firstName, lastName, email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Registration successful!");
    }

    public void login(String email, String password) throws ISOStoreException {

        boolean isMailExist = customerDAO.existByEmail(email);
        if (!isMailExist) {
            throw new ISOStoreException(ExceptionMessages.LOGIN_FAILED);
        }

        String hashedPassword = PasswordUtil.hash(password);
        Customer foundCustomer = customerDAO.findByEmail(email);

        if (foundCustomer != null) {
            boolean passwordEquals = foundCustomer.getPassword().equals(hashedPassword);
            if (!passwordEquals) {
                throw new ISOStoreException(ExceptionMessages.LOGIN_FAILED);
            }else System.out.println("Login successful!");
        }
    }
}
