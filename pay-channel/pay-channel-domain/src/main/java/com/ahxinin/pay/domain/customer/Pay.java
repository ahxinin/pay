package com.ahxinin.pay.domain.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pay {

    //交易数据
    String tradeData;

    public static Pay buildData(String data){
        return Pay.builder()
                .tradeData(data)
                .build();
    }
}
