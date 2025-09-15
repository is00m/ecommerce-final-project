package com.iso.ecommerce.services;

import com.iso.ecommerce.dao.PaymentDao;
import com.iso.ecommerce.model.Order;
import com.iso.ecommerce.model.Payment;
import com.iso.ecommerce.model.enums.PaymentMethod;

public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDao();
    }


    public Payment save(Order order, PaymentMethod paymentMethod){
        Payment payment = new Payment(order, paymentMethod);

        paymentDao.save(payment);
        return payment;
    }
}
