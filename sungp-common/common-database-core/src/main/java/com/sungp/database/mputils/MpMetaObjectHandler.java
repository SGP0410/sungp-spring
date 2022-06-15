package com.sungp.database.mputils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sungp.database.sequence.Sequence;
import com.sungp.database.sequence.SystemClock;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 公共字段自动写入
 * @author sungp
 * @description: 公共字段自动写入
 * @date 2022年06月13日 22:17
 */
public class MpMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("id", Sequence.generateId(), metaObject);
        this.setFieldValByName("createTime", SystemClock.now(), metaObject);
        this.setFieldValByName("updateTime", SystemClock.now(), metaObject);
        this.setFieldValByName("isDeleted", false, metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", SystemClock.now(), metaObject);
    }
}
