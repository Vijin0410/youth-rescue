package com.rescue.system.mapper;

import com.rescue.system.model.entity.StaffProfile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工扩展表（红娘/管理员）(StaffProfile)表数据库访问层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper
public interface StaffProfileMapper extends BaseMapper<StaffProfile> {

}

