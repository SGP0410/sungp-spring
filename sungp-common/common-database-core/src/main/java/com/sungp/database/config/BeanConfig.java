package com.sungp.database.config;

import com.sungp.database.mputils.MpMetaObjectHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author sungp
 * @description: 创建bean对象
 * @date 2022年06月13日 22:39
 */
public class BeanConfig {

    /**
     * @description: 公共字段自动写入
     * @author sungp
     * @date 2022年06月13日 22:41
     * @return MpMetaObjectHandler
     */
    @Bean
    public MpMetaObjectHandler getMpMetaObjectHandler(){
        return new MpMetaObjectHandler();
    }

}
