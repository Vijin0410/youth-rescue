package com.rescue.system.model.entity;

import java.time.LocalDateTime;

import cn.zhyinfo.common.base.BaseEntity;
import lombok.Data;

/**
 * 普通用户扩展表（相亲资料）(UserProfile)实体类
 *
 * @author makejava
 * @since 2026-03-05 16:18:16
 */
@Data
public class UserProfile extends BaseEntity<Long> {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 性别：0未知 1男 2女
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 身高(cm)
     */
    private Integer height;

    /**
     * 学历
     */
    private String education;

    /**
     * 职业
     */
    private String job;

    /**
     * 年收入
     */
    private String annualIncome;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 微信二维码
     */
    private String wechatQr;

    /**
     * 自我介绍
     */
    private String selfIntro;

    /**
     * 认证状态：0未认证 1已认证
     */
    private Integer authStatus;

    /**
     * 婚姻状况
     */
    private String marriageStatus;

    /**
     * 是否有子女
     */
    private Boolean hasChildren;

    /**
     * 是否想要孩子
     */
    private Boolean wantChildren;

    /**
     * 星座
     */
    private String zodiac;

    /**
     * 兴趣爱好
     */
    private Object hobby;

}

