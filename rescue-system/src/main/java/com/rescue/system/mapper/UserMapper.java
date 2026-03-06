package com.rescue.system.mapper;

import com.rescue.system.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户主表（所有角色共有信息）(User)表数据库访问层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

