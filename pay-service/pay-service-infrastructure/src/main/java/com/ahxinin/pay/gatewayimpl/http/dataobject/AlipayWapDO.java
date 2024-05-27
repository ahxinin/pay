package com.ahxinin.pay.gatewayimpl.http.dataobject;

import cn.hutool.core.date.DateUtil;
import com.ahxinin.pay.constant.PayConstant;
import com.ahxinin.pay.domain.PayOrder;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @description: 支付宝手机网站支付
 * @date : 2024-03-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlipayWapDO {

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

    /**
     * 订单总金额，单位为元，精确到小数点后两位
     */
    private String totalAmount;

    /**
     * 获取支付超时时间
     * @return 支付超时时间
     */
    public String getTimeExpire(){
        Date expireDate = DateUtils.addMinutes(new Date(), PayConstant.EXPIRE_MINUTES);
        return DateUtil.format(expireDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 构建支付参数
     */
    public static AlipayWapDO fromPrepayOrder(PayOrder prepayOrder){
        return AlipayWapDO.builder()
                .subject(prepayOrder.getName())
                .outTradeNo(String.valueOf(prepayOrder.getId()))
                .totalAmount(prepayOrder.getAmountWithYuan())
                .build();
    }
}
