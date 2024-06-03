package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付回调
 * @date : 2024-03-22
 */
@Data
public class WeixinPayNotifyCO implements Serializable {

    /**
     * 通知的唯一ID
     */
    private String id;

    /**
     * 通知创建时间
     */
    private String create_time;

    /**
     * 通知类型
     */
    private String event_type;

    /**
     * 通知数据类型
     */
    private String resource_type;

    /**
     * 通知摘要
     */
    private String summary;

    /**
     * 通知请求头
     */
    private WeixinPayNotifyHeaderCO header;
}
