package com.ahxinin.pay.dto.clientobject;

import com.ahxinin.pay.enums.PayOrderStateEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * @description: 支付结果
 * @date : 2024-03-26
 */
@Data
public class PayResultCO implements Serializable {

    /**
     * 支付订单状态
     * see {@link PayOrderStateEnum}
     */
    private String state;

    public static PayResultCO init(String state){
        PayResultCO payResultCO = new PayResultCO();
        payResultCO.setState(state);
        return payResultCO;
    }
}
