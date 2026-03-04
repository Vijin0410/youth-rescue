package com.rescue.system.service;

import com.rescue.system.model.form.LoginReq;
import com.rescue.system.model.form.LoginResp;

/**
 * 认证服务接口
 */
public interface IAuthService {

    /**
     * 登录
     * @param req 登录请求
     * @return 登录响应
     */
    LoginResp login(LoginReq req);

    /**
     * 注册
     * @param req 注册请求（复用登录请求参数）
     */
    void register(LoginReq req);
}
