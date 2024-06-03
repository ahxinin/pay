package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 支付结果
 * @date : 2024-03-26
 */
@Data
public class PayEventCO implements Serializable {

    /**
     * 支付状态
     */
    private String payState;

    public static PayEventCO init(String payState){
        PayEventCO payResultCO = new PayEventCO();
        payResultCO.setPayState(payState);
        return payResultCO;
    }
}
