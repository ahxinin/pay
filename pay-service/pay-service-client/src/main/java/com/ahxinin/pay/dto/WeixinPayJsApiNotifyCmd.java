package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付jsApi支付通知
 * @date : 2024-03-05
 */
@Data
public class WeixinPayJsApiNotifyCmd implements Serializable {

    /**
     * 通知的唯一ID
     */
    private String id;

    /**
     * 通知创建的时间
     */
    private String create_time;

    /**
     * 通知的类型
     */
    private String event_type;

    /**
     * 通知的资源类型
     */
    private String resource_type;

    /**
     * 回调摘要
     */
    private String summary;

    /**
     * 原始请求参数
     */
    private String requestBody;

    /**
     * 原始请求头
     */
    private WeixinPayJsApiNotifyHeaderCmd header;
}
