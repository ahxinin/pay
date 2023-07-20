package com.ahxinin.pay.customer;

import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.data.PayDTO;
import com.alibaba.cola.dto.MultiResponse;
//import org.apache.dubbo.config.annotation.DubboService;

//@DubboService
public class PayServiceImpl implements PayServiceI {

    @Override
    public MultiResponse<PayDTO> pay(PayCmd payCmd) {
        return null;
    }
}
