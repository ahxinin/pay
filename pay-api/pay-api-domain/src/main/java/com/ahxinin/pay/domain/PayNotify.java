package com.ahxinin.pay.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付通知返回值
 * @date : 2024-05-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNotify implements Serializable {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 返回消息
     */
    private String returnMessage;

    /**
     * 需要解锁翻译
     */
    private Boolean needUnlockTranslate;

    /**
     * 需要解锁翻译内容
     */
    public boolean unlockTranslate(){
        return needUnlockTranslate!=null && needUnlockTranslate;
    }
}
