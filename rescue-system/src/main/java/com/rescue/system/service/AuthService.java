package com.rescue.system.service;

import com.rescue.system.model.form.LoginReq;
import com.rescue.system.model.form.LoginResp;

public interface AuthService {
    LoginResp login(LoginReq req);
    void register(LoginReq req);
    void logout();
}
