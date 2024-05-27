package com.ahxinin.pay.dto;

import com.ahxinin.pay.contants.PlanConstant;
import com.alibaba.cola.extension.BizScenario;
import java.io.Serializable;
import lombok.Data;

/**
 * @description: 获取支付结果
 * @date : 2024-03-26
 */
@Data
public class GetPayResultCmd implements Serializable {

    private Long payOrderId;

    private String payType;

    /**
     * 获取扩展识别点
     */
    public BizScenario getBizScenario(){
        return BizScenario.valueOf(PlanConstant.BIZ_ID, payType);
    }
}
