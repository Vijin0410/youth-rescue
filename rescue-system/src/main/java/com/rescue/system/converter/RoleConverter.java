package com.rescue.system.converter;

import com.rescue.system.model.entity.Role;
import com.rescue.system.model.form.RoleForm;
import com.rescue.system.model.vo.RoleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 角色表（权限控制）对象转换器
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    RoleForm entityToForm(Role role);

    RoleVO entityToVo(Role role);

    List<RoleVO> entityToVo(List<Role> role);

    Role formToEntity(RoleForm roleForm);

    List<Role> formToEntity(List<RoleForm> roleForm);

    Page<RoleVO> entityToVOForPage(Page<Role> bo);


}
