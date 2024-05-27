package com.ahxinin.pay.gatewayimpl.http.dataobject;

import com.ahxinin.pay.constant.PayConstant;
import com.ahxinin.pay.domain.PayOrder;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @description: 微信jsApi支付
 * @date : 2024-03-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeixinPayJsApiDO {

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 订单总金额，单位为分
     */
    private Integer total;

    /**
     * 用户在普通商户AppID下的唯一标识
     */
    private String openId;

    /**
     * jsApi下单
     * @param appId 公众号/服务号Id
     * @param mchId 商户号
     * @return 下单参数
     */
    public PrepayRequest buildPrepay(String appId, String mchId){
        PrepayRequest request = new PrepayRequest();
        request.setAppid(appId);
        request.setMchid(mchId);
        request.setDescription(description);
        request.setOutTradeNo(outTradeNo);
        request.setNotifyUrl(notifyUrl);
        request.setAmount(buildAmount());
        request.setPayer(buildPayer());
        request.setTimeExpire(getTimeExpire());
        return request;
    }

    /**
     * 构建支付参数
     */
    public static WeixinPayJsApiDO fromPrepayOrder(PayOrder prepayOrder){
        return WeixinPayJsApiDO.builder()
                .outTradeNo(String.valueOf(prepayOrder.getId()))
                .description(prepayOrder.getName())
                .total(prepayOrder.getAmount().intValue())
                .openId(prepayOrder.getWeixinMpOpenId())
                .build();
    }

    /**
     * 增加通知地址
     */
    public void addNotifyUrl(String notifyUrl){
        this.notifyUrl = notifyUrl;
    }

    /**
     * 订单金额
     */
    private Amount buildAmount(){
        Amount amount = new Amount();
        amount.setTotal(total);
        amount.setCurrency("CNY");
        return amount;
    }

    /**
     * 支付者
     */
    private Payer buildPayer(){
        Payer payer = new Payer();
        payer.setOpenid(openId);
        return payer;
    }

    /**
     * 获取支付超时时间
     * @return 支付超时时间
     */
    private static String getTimeExpire(){
        Date expireDate = DateUtils.addMinutes(new Date(), PayConstant.EXPIRE_MINUTES);
        // 创建一个日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        // 设置时区为UTC
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(expireDate);
    }

    public static void main(String[] args){
        System.out.println(getTimeExpire());
    }
}
