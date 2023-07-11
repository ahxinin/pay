package com.ahxinin.pay.domain.customer.gateway;

import com.ahxinin.pay.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
