package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 微信号公众号初始化
 * @date : 2024-03-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeixinMpInitCO implements Serializable {

    private String url;

    public static WeixinMpInitCO init(String url){
        return WeixinMpInitCO.builder().url(url).build();
    }
}
