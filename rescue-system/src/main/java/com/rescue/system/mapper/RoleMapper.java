package com.rescue.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rescue.system.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色Mapper
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
