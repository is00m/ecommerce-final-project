package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.CategoryDAO;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) throws ISOStoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new ISOStoreException(ExceptionMessages.NO_PERMISSION);
        }

        categoryDAO.save(new Category(name, user, user));
        System.out.println("Category created!");
    }
}
