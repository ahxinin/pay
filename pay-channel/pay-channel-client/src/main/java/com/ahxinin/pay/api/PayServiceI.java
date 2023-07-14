package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.data.PayDTO;
import com.alibaba.cola.dto.MultiResponse;

/**
 * 支付能力
 */
public interface PayServiceI {

    //支付
    MultiResponse<PayDTO> pay(PayCmd payCmd);
}
