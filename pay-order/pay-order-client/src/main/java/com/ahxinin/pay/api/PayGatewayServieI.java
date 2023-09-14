package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.UnionPayCmd;

/**
 * @description: 支付网关
 * @date : 2023-09-14
 */
public interface PayGatewayServieI {

    /**
     * 统一支付
     */
    String unionPay(UnionPayCmd unionPayCmd);
}
