package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.OrderDAO;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.model.Order;
import com.iso.ecommerce.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public Order save(Customer customer, List<Product> products){

        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);
        order.setTotalAmount(totalAmount);
        orderDAO.save(order);

        return order;
    }

    public List<Order> getAll() {
        return orderDAO.findAll();
    }
}
