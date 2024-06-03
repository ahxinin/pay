package com.ahxinin.pay.dto.clientobject;

import lombok.Data;

/**
 * @description: 使用套餐
 * @date : 2024-03-20
 */
@Data
public class UsePlanCO {

    /**
     * 授权ID
     */
    private String authId;

    /**
     * 授权名称
     */
    private String authName;

    /**
     * 支付金额
     */
    private Long payPrice;

    /**
     * 二维码
     */
    private String qrCode;

    public void updateQrCode(String qrCode){
        this.qrCode = qrCode;
    }
}
