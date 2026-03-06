package com.rescue.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 员工扩展表（红娘/管理员）列表对象
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "员工扩展表（红娘/管理员）列表对象")
public class StaffProfileVO {

    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "工号")
    private String employeeNo;

    @Schema(description = "部门")
    private String department;

    @Schema(description = "入职日期")
    private LocalDateTime joinDate;

    @Schema(description = "服务区域")
    private String serviceArea;

    @Schema(description = "个人介绍")
    private String introduction;

    @Schema(description = "联系电话")
    private String contactPhone;

}

