package com.rescue.system.model.query;

import com.rescue.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询用户主表（所有角色共有信息）
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "查询用户主表（所有角色共有信息）对象")
public class UserPageQuery extends BasePageQuery {

}

