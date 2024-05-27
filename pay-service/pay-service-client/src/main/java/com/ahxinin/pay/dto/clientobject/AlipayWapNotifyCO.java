package com.ahxinin.pay.dto.clientobject;

import com.alibaba.fastjson2.JSONObject;
import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * @description: 支付宝手机网页支付回调
 * @date : 2024-03-20
 */
@Data
public class AlipayWapNotifyCO implements Serializable {

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

    /**
     * 交易支付成功
     */
    public boolean tradeSuccess(){
        return "TRADE_SUCCESS".equals(trade_status);
    }

    /**
     * 交易已关闭
     */
    public boolean tradeClosed(){
        return "TRADE_CLOSED".equals(trade_status);
    }

    /**
     * 交易已结束
     */
    public boolean tradeFinished(){
        return "TRADE_FINISHED".equals(trade_status);
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
