package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信公众号初始化
 * @date : 2024-03-18
 */
@Data
public class WeixinMpInitCmd implements Serializable {

    private Long tradeId;

    private String tradeType;

    public String buildUrlParam(){
        return "?tradeId=" + tradeId + "&tradeType=" + tradeType;
    }
}
