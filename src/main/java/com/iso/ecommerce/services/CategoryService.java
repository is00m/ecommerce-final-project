package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.CategoryDAO;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);
        System.out.println("Category successfully deleted!");
    }

    public void save(String name, User user) throws ISOStoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new ISOStoreException(ExceptionMessages.NO_PERMISSION);
        }

        categoryDAO.save(new Category(name, user, user));
        System.out.println("Category created!");
    }

    public List<Category> getall() {
        return categoryDAO.findAll();
    }

    public Category getById(Long categoryId) throws ISOStoreException {
        Category foundCategory = categoryDAO.findById(categoryId);

        if (foundCategory == null){
            throw new ISOStoreException(ExceptionMessages.CATEGORY_NOT_FOUND);
        }
        System.out.println("Category found = " + foundCategory);
        return foundCategory;
    }
}
