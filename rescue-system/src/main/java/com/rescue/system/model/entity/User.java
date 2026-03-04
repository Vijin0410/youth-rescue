package com.rescue.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rescue.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class User extends BaseEntity<Long> {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 状态（0：正常，1：禁用）
     */
    private Integer status;

    /**
     * 头像
     */
    private String avatar;
}
