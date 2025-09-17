package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.ProductDAO;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Product;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void save(Product product, User user) throws ISOStoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new ISOStoreException(ExceptionMessages.NO_PERMISSION);
        }

        product.setCreatedUser(user);
        product.setUpdatedUser(user);
        productDAO.save(product);
        System.out.println("Product successfully saved!");

    }

    public List<Product> getAll(){

        return productDAO.findAll();
    }

    public void deleteId(long id) {
        productDAO.delete(id);
        System.out.println("Product successfully deleted: " + id);
    }
}
