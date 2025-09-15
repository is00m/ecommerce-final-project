package com.iso.ecommerce.dao;

public interface BaseDAO<T> {

    void save(T t);

    T findById(long id);

    void update(T t);

    void delete(long id);
}
