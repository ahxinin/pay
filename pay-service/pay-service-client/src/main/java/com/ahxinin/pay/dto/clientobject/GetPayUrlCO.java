package com.ahxinin.pay.dto.clientobject;

import com.ahxinin.pay.enums.TradeTypeEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * @description: 获取支付链接
 * @date : 2024-03-26
 */
@Data
public class GetPayUrlCO implements Serializable {

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易ID
     */
    private String tradeId;

    /**
     * 创建资源支付链接
     */
    public static GetPayUrlCO ofResource(String tradeId){
        GetPayUrlCO getPayUrlCO = new GetPayUrlCO();
        getPayUrlCO.setTradeType(TradeTypeEnum.BRAILLE_RESOURCE.getCode());
        getPayUrlCO.setTradeId(tradeId);
        return getPayUrlCO;
    }

    /**
     * 创建套餐支付链接
     */
    public static GetPayUrlCO ofPlan(String tradeId) {
        GetPayUrlCO getPayUrlCO = new GetPayUrlCO();
        getPayUrlCO.setTradeType(TradeTypeEnum.USER_PLAN.getCode());
        getPayUrlCO.setTradeId(tradeId);
        return getPayUrlCO;
    }
}
