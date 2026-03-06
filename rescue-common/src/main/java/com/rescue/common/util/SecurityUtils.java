package com.rescue.common.util;

import cn.hutool.core.convert.Convert;

import com.rescue.common.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security 工具类
 *
 * @author lenovo
 */
@Slf4j
public class SecurityUtils {

  public static Long getUserId() {
    try {
      return Optional.ofNullable(Convert.toLong(getTokenAttributes().get("userId"))).orElse(0L);
    } catch (Exception e) {
      log.warn("后台调用，返回默认用户id:0L");
    }
    return 0L;
  }

  public static String getNickname() {
    try {
      return Optional.ofNullable(getTokenAttributes().get("nickname")).orElse("").toString();
    } catch (Exception e) {
      log.warn("后台调用，返回默认用户昵称:''");
    }
    return "";
  }

  public static String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  public static Map<String, Object> getTokenAttributes() {
    Map<String, Object> map;
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
      map = jwtAuthenticationToken.getTokenAttributes();
    } catch (Exception e) {
      map = Collections.emptyMap();
    }
    return map;
  }

  /** 获取用户角色集合 */
  public static Set<String> getRoles() {
    Set<String> roles = null;
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      roles =
          AuthorityUtils.authorityListToSet(authentication.getAuthorities()).stream()
              .collect(
                  Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
    } catch (Exception e) {
      log.warn("后台调用，无法获取到当前登录用户信息");
    }
    return roles;
  }

  /** 获取部门ID,默认返回-1L，一个不存在的部门ID */
  public static Long getDeptId() {
    return Convert.toLong(Optional.ofNullable(getTokenAttributes().get("deptId")).orElse(-1L));
  }

//  /**
//   * 获取租户ID
//   *
//   * @return
//   */
//  public static Long getTenantId() {
//    return Convert.toLong(Optional.ofNullable(getTokenAttributes().get("tenantId")).orElse(0l));
//  }
//
//  /**
//   * 获取租户ID
//   *
//   * @return
//   */
//  public static String getTenantCode() {
//    return Convert.toStr(Optional.ofNullable(getTokenAttributes().get("tenantCode")).orElse(""));
//  }

  public static boolean isRoot() {
    Set<String> roles = getRoles();
    if (roles == null || roles.isEmpty()) {
      return false;
    }
    return roles.contains(GlobalConstants.ROOT_ROLE_CODE)
        || roles.contains(GlobalConstants.ROOT_ADMIN_CODE)
        || roles.contains(GlobalConstants.ROOT_ROLE_CODE.toLowerCase())
        || roles.contains(GlobalConstants.ROOT_ADMIN_CODE.toUpperCase());
  }

  public static String getJti() {
    return String.valueOf(getTokenAttributes().get("jti"));
  }

  public static Long getExp() {
    return Convert.toLong(getTokenAttributes().get("exp"));
  }

  /**
   * 获取最大数据权限范围, 1:全部数据权限3:本部门数据权限 2:本部门及以下数据权限 4:仅本人数据权限 5:自定数据权限 默认返回本人数据
   *
   * @return
   */
  public static Integer getMaxDataScope() {
    return Convert.toInt(Optional.ofNullable(getTokenAttributes().get("maxDataScope")).orElse(4));
  }
}
