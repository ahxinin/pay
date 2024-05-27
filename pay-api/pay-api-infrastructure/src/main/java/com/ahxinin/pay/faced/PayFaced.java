package com.ahxinin.pay.faced;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.dto.clientobject.GetPayUrlCO;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @description: 支付路径
 * @date : 2024-04-17
 */
@Slf4j
@Component
public class PayFaced {

    @DubboReference
    private PayServiceI payService;

    /**
     * 获取支付链接
     */
    public String getPayQRCode(GetPayUrlCO getPayUrlCO) {
        SingleResponse<String> response = payService.getPayUrl(getPayUrlCO);
        if (!response.isSuccess()) {
            throw new BizException(response.getErrMessage());
        }
        String payUrl = response.getData();
        return authQRCode(payUrl);
    }

    /**
     * 生成支付二维码
     */
    private String authQRCode(String url) {
        QrConfig qrConfig = new QrConfig(200, 200);
        return QrCodeUtil.generateAsBase64(url, qrConfig, "jpg");
    }
}
