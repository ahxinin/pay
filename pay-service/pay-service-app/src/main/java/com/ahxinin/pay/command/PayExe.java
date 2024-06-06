package com.ahxinin.pay.command;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.extension.PayExtPt;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 资源支付
 * @date : 2024-03-14
 */
@Slf4j
@Component
public class PayExe {

//    @Autowired
//    private ExtensionExecutor extensionExecutor;

    public PayCO execute(PayOrder payOrder){
//        return extensionExecutor.execute(PayExtPt.class, payOrder.getBizScenario(), ex -> ex.pay(payOrder));
        return null;
    }

    public PayOrderResult queryPayResult(GetPayResultCmd getPayResultCmd){
//        return extensionExecutor.execute(PayExtPt.class, getPayResultCmd.getBizScenario(), ex -> ex.queryPayResult(getPayResultCmd));
        return null;
    }
}
