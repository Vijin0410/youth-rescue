package com.rescue.system.model.entity;

import java.time.LocalDateTime;

import cn.zhyinfo.common.base.BaseEntity;
import lombok.Data;

/**
 * 员工扩展表（红娘/管理员）(StaffProfile)实体类
 *
 * @author makejava
 * @since 2026-03-05 16:18:13
 */
@Data
public class StaffProfile extends BaseEntity<Long> {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 工号
     */
    private String employeeNo;

    /**
     * 部门
     */
    private String department;

    /**
     * 入职日期
     */
    private LocalDateTime joinDate;

    /**
     * 服务区域
     */
    private String serviceArea;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 联系电话
     */
    private String contactPhone;

}

