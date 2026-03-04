package com.rescue.system.controller;

import com.rescue.common.result.Result;
import com.rescue.system.model.form.LoginReq;
import com.rescue.system.model.form.LoginResp;
import com.rescue.system.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResp> login(@RequestBody LoginReq req) {
        return Result.success(authService.login(req));
    }

    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<Void> register(@RequestBody LoginReq req) {
        authService.register(req);
        return Result.success();
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Result<Void> logout() {
        // 由于是无状态JWT，后端无需特殊处理，前端丢弃Token即可
        // 如果有Redis黑名单机制，在此处加入黑名单
        return Result.success();
    }
}
