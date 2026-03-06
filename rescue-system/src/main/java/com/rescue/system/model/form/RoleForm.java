package com.rescue.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 角色表（权限控制）表单信息
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "角色表（权限控制）表单信息")
public class RoleForm {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色标识")
    private String name;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "权限列表")
    private String permissions;

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "更新人ID")
    private Long updateBy;

}

