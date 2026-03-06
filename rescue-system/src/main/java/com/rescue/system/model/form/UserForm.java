package com.rescue.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户主表（所有角色共有信息）表单信息
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "用户主表（所有角色共有信息）表单信息")
public class UserForm {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "微信openid")
    private String openid;

    @Schema(description = "微信unionid")
    private String unionid;

    @Schema(description = "微信会话密钥")
    private String sessionKey;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像路径")
    private String avatar;

    @Schema(description = "角色ID")
    private Integer roleId;

    @Schema(description = "状态：1正常 2禁用 3审核")
    private Integer status;

    @Schema(description = "最后活跃时间")
    private LocalDateTime lastActiveTime;

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "更新人ID")
    private Long updateBy;

}

