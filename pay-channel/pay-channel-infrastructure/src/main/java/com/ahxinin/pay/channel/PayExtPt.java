package com.ahxinin.pay.channel;

import com.ahxinin.pay.domain.exception.PayException;
import com.ahxinin.pay.dto.PayCmd;
import com.alibaba.cola.extension.ExtensionPointI;

public interface PayExtPt extends ExtensionPointI {

    String pay(PayCmd payCmd) throws PayException;
}
