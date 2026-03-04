package com.rescue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rescue.system.model.entity.User;

public interface UserService extends IService<User> {
    User getByUsername(String username);
}
