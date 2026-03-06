package com.rescue.system.model.entity;

import java.time.LocalDateTime;

import cn.zhyinfo.common.base.BaseEntity;
import lombok.Data;

/**
 * 用户主表（所有角色共有信息）(User)实体类
 *
 * @author makejava
 * @since 2026-03-05 16:18:14
 */
@Data
public class User extends BaseEntity<Long> {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 微信unionid
     */
    private String unionid;

    /**
     * 微信会话密钥
     */
    private String sessionKey;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 状态：1正常 2禁用 3审核
     */
    private Integer status;

    /**
     * 最后活跃时间
     */
    private LocalDateTime lastActiveTime;

}

