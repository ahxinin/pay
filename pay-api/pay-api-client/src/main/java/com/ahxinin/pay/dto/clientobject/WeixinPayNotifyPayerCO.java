package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付回调付款人数据
 * @date : 2024-03-22
 */
@Data
public class WeixinPayNotifyPayerCO implements Serializable {

    /**
     * 用户标识
     */
    private String openid;
}
