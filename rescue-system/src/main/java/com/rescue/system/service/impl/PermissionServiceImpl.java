package com.rescue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rescue.system.model.entity.UserRole;
import com.rescue.system.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验服务
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl {

    private final UserRoleMapper userRoleMapper;

    /**
     * 获取用户角色ID列表
     */
    public List<Long> getRoleIdsByUserId(Long userId) {
        return userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

    /**
     * 校验是否有权限 (占位)
     */
    public boolean hasPermission(Long userId, String permission) {
        // TODO: 实现具体的权限校验逻辑
        return true;
    }
}
