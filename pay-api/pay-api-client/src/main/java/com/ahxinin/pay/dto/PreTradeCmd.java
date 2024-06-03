package com.ahxinin.pay.dto;

import com.ahxinin.pay.constants.TradeConstant;
import com.alibaba.cola.extension.BizScenario;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 预支付交易订单
 * @date : 2024-03-18
 */
@Data
public class PreTradeCmd implements Serializable {

    /**
     * 交易类型
     * {@link com.cometlight.braille.enums.TradeTypeEnum}
     */
    @NotNull(message = "交易类型不能为空")
    private String tradeType;

    /**
     * 交易id
     */
    @NotNull(message = "交易id不能为空")
    private Long tradeId;

    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private String payType;

    /**
     * 微信公众号openid
     */
    private String weixinMpOpenId;

    /**
     * 校验微信支付参数
     */
    public void checkWeixinPayParam(){
        if(StringUtils.isEmpty(weixinMpOpenId)){
            throw new IllegalArgumentException("微信支付需要微信公众号openid");
        }
    }

    /**
     * 获取业务场景
     */
    public BizScenario getBizScenario(){
        return BizScenario.valueOf(TradeConstant.BIZ_ID, tradeType);
    }
}
