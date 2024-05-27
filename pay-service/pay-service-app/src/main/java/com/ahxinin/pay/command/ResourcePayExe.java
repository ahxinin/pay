package com.ahxinin.pay.command;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.extension.PayExtPt;
import com.alibaba.cola.extension.ExtensionExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 资源支付
 * @date : 2024-03-14
 */
@Slf4j
@Component
public class ResourcePayExe {

    @Resource
    private ExtensionExecutor extensionExecutor;

    public PayCO execute(PayOrder payOrder){
        return extensionExecutor.execute(PayExtPt.class, payOrder.getBizScenario(), ex -> ex.pay(payOrder));
    }

    public PayOrderResult queryPayResource(GetPayResultCmd getPayResultCmd){
        return extensionExecutor.execute(PayExtPt.class, getPayResultCmd.getBizScenario(), ex -> ex.queryPayResult(getPayResultCmd));
    }
}
