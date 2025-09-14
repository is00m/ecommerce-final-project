package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.ProductDAO;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }
}
