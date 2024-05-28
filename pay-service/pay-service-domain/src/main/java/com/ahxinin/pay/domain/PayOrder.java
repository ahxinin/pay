package com.ahxinin.pay.domain;

import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.enums.PayOrderStateEnum;
import com.ahxinin.pay.enums.TradeTypeEnum;
import com.alibaba.cola.extension.BizScenario;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class PayOrder {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易标识符
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
     * 付费模式
     * see {@link PayWayEnum}
     */
    private String payWay;

    /**
     * 支付方式
     * see {@link PayTypeEnum}
     */
    private String payType;

    /**
     * 第三方支付账号
     */
    private String thirdAccount;

    /**
     * 支付订单状态
     * see {@link PayOrderStateEnum}
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
     * 金额四舍五入转换为以元为单位
     */
    public String getAmountWithYuan(){
        BigDecimal amountDecimal = BigDecimal.valueOf(amount);
        BigDecimal resultDecimal = amountDecimal.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return resultDecimal.toPlainString();
    }

    /**
     * 获取扩展识别点
     */
    public BizScenario getBizScenario(){
        return BizScenario.valueOf(PlanConstant.BIZ_ID, payType);
    }

    /**
     * 添加Id
     */
    public void addId(Long id){
        this.id = id;
    }

    /**
     * 初始化状态
     */
    public void initState(){
        this.state = PayOrderStateEnum.PENDING.getCode();
    }

    /**
     * 交易成功
     */
    public Boolean success(){
        return PayOrderStateEnum.PAID.getCode().equals(state);
    }

    /**
     * 交易取消
     */
    public Boolean cancel(){
        return PayOrderStateEnum.CANCEL.getCode().equals(state);
    }

    /**
     * 交易完成
     */
    public void paid(){
        this.state = PayOrderStateEnum.PAID.getCode();
    }

    /**
     * 更新交易凭证
     */
    public void updateTrade(String tradeFileUrl){
        this.tradeFileUrl = tradeFileUrl;
    }

    /**
     * 更新实际支付金额
     */
    public void updatePayAmount(){
        discount = discount==null ? 0 : discount;
        this.payAmount = amount = discount;
    }

    public String toString(){
        return "PayOrder(id=" + this.getId() + ", tradeType=" + this.getTradeType() + ", tradeId=" + this.getTradeId() + ", amount=" + this.getAmount() + ", name=" + this.getName() + ", payType=" + this.getPayType() + ", thirdAccount=" + this.getThirdAccount() + ", state=" + this.getState() + ")";
    }
}
