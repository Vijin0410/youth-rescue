package com.rescue.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 角色表（权限控制）列表对象
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "角色表（权限控制）列表对象")
public class RoleVO {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色标识")
    private String name;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "权限列表")
    private String permissions;

}

