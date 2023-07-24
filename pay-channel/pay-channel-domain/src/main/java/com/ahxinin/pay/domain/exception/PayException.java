package com.ahxinin.pay.domain.exception;

import com.ahxinin.pay.domain.customer.Pay;

public class PayException extends RuntimeException{

    String message;

    public PayException(String message){
        super(message);
    }
}
