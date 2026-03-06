package com.rescue.system.mapper;

import com.rescue.system.model.entity.UserProfile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 普通用户扩展表（相亲资料）(UserProfile)表数据库访问层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

}

