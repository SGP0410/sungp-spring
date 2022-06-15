package com.sungp.database.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础实体类
 * @author sungp
 * @description: 基础实体类
 * @date 2022年03月05日 20:04
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    @TableField(fill = FieldFill.INSERT)
    private String id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
    /**
     * 是否删除
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Boolean isDeleted;

}
