package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Product;

import java.sql.Connection;

public class ProductDAO {
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product){

    }
}
