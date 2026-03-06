package com.rescue.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询员工扩展表（红娘/管理员）
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "查询员工扩展表（红娘/管理员）对象")
public class StaffProfilePageQuery extends BasePageQuery {

}

