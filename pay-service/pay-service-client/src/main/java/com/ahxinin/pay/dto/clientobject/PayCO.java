package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 预支付返回值
 * @date : 2024-03-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayCO implements Serializable {

    private WeixinPayCO weixinPayParam;

    private String aliPayParam;

    public static PayCO buildAlipayParam(String aliPayParam)
            throws UnsupportedEncodingException {
        return PayCO.builder()
                .aliPayParam(aliPayParam)
                .build();
    }

    public static PayCO buildWeixinPayParam(WeixinPayCO weixinPayParam){
        return PayCO.builder()
                .weixinPayParam(weixinPayParam)
                .build();
    }
}
