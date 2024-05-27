package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信公众号授权
 * @date : 2024-03-15
 */
@Data
public class WeixinMpAuthCmd implements Serializable {

    /**
     * 微信授权码
     */
    private String code;
}
