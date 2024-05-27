package com.ahxinin.pay.dto;

import com.ahxinin.pay.enums.PayOrderStateEnum;
import com.alibaba.cola.dto.PageQuery;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付订单查询
 * @date : 2024-03-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListPayOrderQuery extends PageQuery implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 交易标识符
     */
    private String tradeId;

    /**
     * 支付方式
     * see {@link PayTypeEnum}
     */
    private String payType;

    /**
     * 支付订单状态
     * see {@link PayOrderStateEnum}
     */
    private String state;

    /**
     * 交易开始时间
     */
    private Long startTime;

    /**
     * 交易结束时间
     */
    private Long endTime;

    /**
     * 用户ID
     */
    private Long userId;
}
