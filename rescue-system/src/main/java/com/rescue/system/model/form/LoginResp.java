package com.rescue.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 登录响应
 */
@Data
@Builder
@Schema(description = "登录响应结果")
public class LoginResp {

    @Schema(description = "访问Token")
    private String token;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "角色列表")
    private List<String> roles;
}
