package com.rescue.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录请求
 */
@Data
@Schema(description = "登录请求参数")
public class LoginReq {

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码")
    private String code;
    
    @Schema(description = "登录类型：1-密码登录，2-验证码登录")
    private Integer type = 1;
}
