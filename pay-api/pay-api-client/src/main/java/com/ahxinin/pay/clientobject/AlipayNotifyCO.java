package com.ahxinin.pay.clientobject;

import com.alibaba.fastjson2.JSONObject;
import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * @description: 支付宝回调
 * @date : 2024-03-20
 */
@Data
public class AlipayNotifyCO implements Serializable {

    /**
     * 支付宝交易号
     */
    private String trade_no;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 交易状态
     */
    private String trade_status;

    /**
     * 原始通知参数
     */
    private Map<String, String> notifyParams;

    public static AlipayNotifyCO build(Map<String, String> params){
        AlipayNotifyCO alipayNotifyCO = JSONObject.parseObject(JSONObject.toJSONString(params), AlipayNotifyCO.class);
        alipayNotifyCO.setNotifyParams(params);
        return alipayNotifyCO;
    }
}
