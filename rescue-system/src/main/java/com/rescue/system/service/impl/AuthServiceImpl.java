package com.rescue.system.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rescue.common.result.ResultCode;
import com.rescue.common.web.exception.BizException;
import com.rescue.framework.security.JwtUtils;
import com.rescue.system.model.form.LoginReq;
import com.rescue.system.model.form.LoginResp;
import com.rescue.system.model.entity.User;
import com.rescue.system.mapper.UserMapper;
import com.rescue.system.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    @Override
    public LoginResp login(LoginReq req) {
        // 根据手机号查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, req.getPhone()));

        if (user == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }

        if (req.getType() == 1) {
            // 密码登录
            if (!BCrypt.checkpw(req.getPassword(), user.getPassword())) {
                throw new BizException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
            }
        } else {
            // 验证码登录 (TODO: 验证码校验逻辑)
            // if (!checkCode(req.getPhone(), req.getCode())) { ... }
        }

        if (user.getStatus() == 1) {
            throw new BizException(ResultCode.USER_ACCOUNT_LOCKED);
        }

        // 生成Token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        String token = jwtUtils.generateToken(user.getUsername(), claims);

        return LoginResp.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .realName(user.getRealName())
                .avatar(user.getAvatar())
                .roles(Collections.emptyList()) // TODO: 查询角色
                .build();
    }

    @Override
    public void register(LoginReq req) {
        // 检查手机号是否已存在
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, req.getPhone()));
        if (count > 0) {
            throw new BizException("手机号已存在");
        }

        User user = new User();
        user.setPhone(req.getPhone());
        user.setUsername(req.getPhone()); // 默认用户名为手机号
        user.setPassword(BCrypt.hashpw(req.getPassword()));
        user.setStatus(0);
        user.setRealName("用户" + req.getPhone().substring(7));
        
        userMapper.insert(user);
    }
}
