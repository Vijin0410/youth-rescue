package com.rescue.common.constant;

/**
 * @author lenovo
 */
public interface SecurityConstants {

  /** 用户ID字段 */
  public static final String DETAILS_USER_ID = "user_id";

  /** 用户名字段 */
  public static final String DETAILS_USERNAME = "username";

  /** 用户标识 */
  public static final String USER_KEY = "user_key";

  /** 黑名单TOKEN Key前缀 */
  String BLACKLIST_TOKEN_PREFIX = "AUTH:BLACKLIST_TOKEN:";

  /** 验证码key前缀 */
  String VERIFY_CODE_CACHE_KEY_PREFIX = "AUTH:VERIFY_CODE:";

  /** 短信验证码key前缀 */
  String SMS_CODE_PREFIX = "AUTH:SMS_CODE:";

  /** 用户权限集合缓存前缀 */
  String USER_PERMS_CACHE_KEY_PREFIX = "AUTH:USER_PERMS:";

  /** 授权信息中的权限或角色的key */
  String AUTHORITIES_CLAIM_NAME_KEY = "authorities";

  /** 授权信息中的CONTENT_TYPE */
  String CONTENT_TYPE = "content-type";

  /** 管理端客户端ID */
  String WEB_CLIENT_ID = "web";

  /** 移动端客户端ID */
  String APP_CLIENT_ID = "app";

  /** Knife4j测试客户端ID（Knife4j自动填充的 access_token 须原生返回，不能被包装成业务码数据格式） */
  String KNIFE4J_TEST_CLIENT_ID = "client";

  /** 验证码key */
  String VERIFY_CODE_KEY = "verifyCodeKey";

  /** 验证码 */
  String VERIFY_CODE = "verifyCode";

  /** 刷新令牌key */
  String REFRESH_TOKEN = "refresh_token";

  /** 手机号 */
  String MOBILE = "mobile";

//  1
//  暴力破解登录
//          ERROELOGIN
  String ERROELOGIN = "ERROELOGIN";
//
//2
//  非法IP访问
//          NOIP
  String NOIP = "NOIP";
//
//3
//  黑名单IP
//          BLACKIP
  String BLACKIP = "BLACKIP";
//
//4
//  请求头告警
//          REFERER
      String REFERER = "REFERER";
//
//5
//  高频率告警
//          MORETIMES
      String MORETIMES = "MORETIMES";
//
//6
//  未知告警
//          UNKNOWN
      String UNKNOWN = "UNKNOWN";

      //时间戳
      String TIMESTAMP = "TIMESTAMP";

      //签名
      String SIGNATURE = "SIGNATURE";

      //防重放
      String NONCE = "NONCE";

      //限流
      String RATE_LIMIT = "RATE_LIMIT";

      String LOW_WARN ="1";
      String MID_WARN ="2";
      String HIGH_WARN ="3";

}
