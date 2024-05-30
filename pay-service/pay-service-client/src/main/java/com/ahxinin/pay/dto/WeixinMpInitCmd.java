package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信公众号初始化
 * @date : 2024-03-18
 */
@Data
public class WeixinMpInitCmd implements Serializable {

    /**
     * 交易类型
     * @see com.ahxinin.pay.enums.TradeTypeEnum
     */
    private String tradeType;

    /**
     * 交易id
     */
    private Long tradeId;

    public String buildUrlParam(){
        return "?tradeType=" + tradeType +"&tradeId=" + tradeId;
    }
}
