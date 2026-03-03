package com.rescue.common.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.rescue.common.util.LocalDateTimeSerializer;
import lombok.Data;

/**
 * 基础实体类
 * @author lenovo
 */
@Data
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private T  id;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JSONField(serializeUsing = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(serializeUsing = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     *删除标识(1:已删除;0:未删除)
     */
    private Integer deleted;

    /**
     * 租户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;
}
