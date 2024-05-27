package com.ahxinin.pay.constant;

/**
 * @description: 支付常量
 * @date : 2024-03-04
 */
public class PayConstant {

    /**
     * 支付超时分钟数
     */
    public static final Integer EXPIRE_MINUTES = 10;

    /**
     * 支付宝-支付超时参数
     */
    public static final String ALIPAY_OPTION_TIME_EXPIRE = "time_expire";

    /**
     * 缓存Key:支付状态
     */
    public static final String CACHE_KEY_STATE = "state";

    /**
     * 缓存Key:支付参数
     */
    public static final String CACHE_KEY_PARAM = "param";
    /**
     * 缓存Key:支付订单
     */
    public static final String CACHE_KEY_PAYORDER = "payOrder";
}
