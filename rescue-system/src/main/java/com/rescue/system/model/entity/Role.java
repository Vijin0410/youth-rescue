package com.rescue.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rescue.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("role")
public class Role extends BaseEntity<Long> {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（0：正常，1：禁用）
     */
    private Integer status;
}
