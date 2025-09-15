package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.UserDAO;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;
import com.iso.ecommerce.util.PasswordUtil;

public class UserService {

    private final UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public void save(String username, String password, Role role) throws ISOStoreException {
        User foundUser = userDao.findByUsername(username);

        if (foundUser != null) {
            throw new ISOStoreException(ExceptionMessages.CUSTOMER_EMAIL_ALREADY_EXITS);
        }
        userDao.save(new User(username, PasswordUtil.hash(password), role));
        System.out.println("Registration successful!");
    }

    public User login(String username, String password) throws ISOStoreException {
        User foundUser = userDao.findByUsername(username);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equals(foundUser.getPasswrd())) {
                throw new ISOStoreException(ExceptionMessages.LOGIN_FAILED);
            }
        } else throw new ISOStoreException(ExceptionMessages.LOGIN_FAILED);
        System.out.println("Login successful!");
        System.out.println("Welcome " + foundUser.getUsername() + "!");
        return foundUser;
    }
}
