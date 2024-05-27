package com.ahxinin.pay.gatewayimpl.inner;

import com.sankuai.inf.leaf.IDGen;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 雪花算法
 * @date : 2024-03-14
 */
@Slf4j
@Component
public class SnowFlakeMapper {

    @Resource
    private IDGen idGen;

    public Long getId(String key) {
        return idGen.get(key).getId();
    }
}
