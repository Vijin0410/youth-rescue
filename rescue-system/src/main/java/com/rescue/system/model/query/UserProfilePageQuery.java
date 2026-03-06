package com.rescue.system.model.query;

import cn.zhyinfo.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询普通用户扩展表（相亲资料）
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "查询普通用户扩展表（相亲资料）对象")
public class UserProfilePageQuery extends BasePageQuery {

}

