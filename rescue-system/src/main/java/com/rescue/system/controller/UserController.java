package com.rescue.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescue.common.result.Result;
import com.rescue.framework.security.SecurityUtils;
import com.rescue.system.model.entity.User;
import com.rescue.system.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/me")
    public Result<User> getCurrentUser() {
        Long userId = SecurityUtils.getUserId();
        return Result.success(userService.getById(userId));
    }

    @Operation(summary = "分页查询用户")
    @GetMapping("/page")
    public Result<Page<User>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String username
    ) {
        Page<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            wrapper.like(User::getUsername, username);
        }
        return Result.success(userService.page(page, wrapper));
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public Result<Boolean> add(@RequestBody User user) {
        return Result.judge(userService.save(user));
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        return Result.judge(userService.updateById(user));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.judge(userService.removeById(id));
    }
}
