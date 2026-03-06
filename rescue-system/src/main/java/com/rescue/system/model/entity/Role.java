package com.rescue.system.model.entity;

import java.time.LocalDateTime;

import cn.zhyinfo.common.base.BaseEntity;
import lombok.Data;

/**
 * 角色表（权限控制）(Role)实体类
 *
 * @author makejava
 * @since 2026-03-05 16:18:11
 */
@Data
public class Role extends BaseEntity<Long> {

    /**
     * 角色标识
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 权限列表
     */
    private String permissions;

}

