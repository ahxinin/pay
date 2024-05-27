package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 微信公众号授权
 * @date : 2024-03-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeixinMpAuthCO implements Serializable {

    /**
     * 微信公众号openId
     */
    private String openId;

    public static WeixinMpAuthCO init(String openId){
        return WeixinMpAuthCO.builder().openId(openId).build();
    }
}
