package com.rescue.framework.security.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.rescue.common.constant.SecurityConstants;
import com.rescue.common.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * SpringSecurity权限校验
 *
 * @author lenvo
 */
@Service("ss")
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

  private final RedisTemplate redisTemplate;

  /**
   * 判断当前登录用户是否拥有操作权限
   *
   * @param perm 权限标识(eg: sys:user:add)
   * @return
   */
  public boolean hasPerm(String perm) {

    if (StrUtil.isBlank(perm)) {
      return false;
    }
    // 超级管理员放行
    if (SecurityUtils.isRoot()) {
      return true;
    }
    Long userId = SecurityUtils.getUserId();

    Object perObj =
        redisTemplate.opsForValue().get(SecurityConstants.USER_PERMS_CACHE_KEY_PREFIX + userId);
    if (Objects.isNull(perObj)) {
      return false;
    }
    // 转set
    Set<String> perms = convertJsonArrayToSet((JSONArray) perObj);
    if (CollectionUtil.isEmpty(perms)) {
      return false;
    }
    boolean hasPermission =
        perms.stream().anyMatch(item -> PatternMatchUtils.simpleMatch(perm, item)); // *号匹配任意字符

    if (!hasPermission) {
      log.error("用户无访问权限");
    }
    return hasPermission;
  }

  public Set<String> convertJsonArrayToSet(JSONArray jsonArray) {
    Set<String> stringSet = new HashSet<>();
    for (Object obj : jsonArray) {
      stringSet.add(obj.toString());
    }
    return stringSet;
  }
}
