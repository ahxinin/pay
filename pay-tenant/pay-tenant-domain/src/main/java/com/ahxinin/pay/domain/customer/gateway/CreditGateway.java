package com.ahxinin.pay.domain.customer.gateway;

import com.ahxinin.pay.domain.customer.Customer;
import com.ahxinin.pay.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
