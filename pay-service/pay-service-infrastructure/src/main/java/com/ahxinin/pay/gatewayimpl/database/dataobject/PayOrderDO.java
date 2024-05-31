package com.ahxinin.pay.gatewayimpl.database.dataobject;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付订单
 * @date : 2024-03-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderDO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易业务类型
     */
    private String tradeType;

    /**
     * 交易业务标识符
     */
    private Long tradeId;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 优惠金额
     */
    private Long discount;

    /**
     * 实付金额
     */
    private Long payAmount;

    /**
     * 交易名称
     */
    private String name;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 第三方支付账号
     */
    private String thirdAccount;

    /**
     * 支付订单状态
     */
    private String state;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 交易凭证url
     */
    private String tradeFileUrl;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 更新时间
     */
    private Long updatedAt;

    /**
     * 创建时间
     */
    private Long createdAt;
}
