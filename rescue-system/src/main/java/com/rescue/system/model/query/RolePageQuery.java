package com.rescue.system.model.query;

import cn.zhyinfo.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询角色表（权限控制）
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "查询角色表（权限控制）对象")
public class RolePageQuery extends BasePageQuery {

}

