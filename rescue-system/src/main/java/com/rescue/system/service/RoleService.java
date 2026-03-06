package com.rescue.system.service;

import com.rescue.system.model.entity.Role;
import com.rescue.system.model.form.RoleForm;
import com.rescue.system.model.vo.RoleVO;
import com.rescue.system.model.query.RolePageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表（权限控制）表服务接口
 *
 * @author makejava
 * @date 2026-03-05
 */
public interface RoleService extends IService<Role> {
    /**
     * 新增角色表（权限控制）
     *
     * @param formData 角色表（权限控制）表单数据
     */
    Long saveRole(RoleForm formData);

    /**
     * 批量新增角色表（权限控制）
     *
     * @param formData 角色表（权限控制）表单数据
     */
    Long batchSaveRole(List<RoleForm> formData);

    /**
     * 更新角色表（权限控制）
     *
     * @param id       主键id
     * @param formData 角色表（权限控制）表单数据
     */
    void updateRole(Long id, RoleForm formData);

    /**
     * 删除角色表（权限控制）
     *
     * @param idStr 角色表（权限控制）IDs
     */
    void deleteRole(String idStr);

    /**
     * 通过ID获取角色表（权限控制）
     *
     * @param id 主键
     * @return RoleForm 表单对象
     */
    RoleVO getRoleVo(Long id);

    /**
     * 分页查询角色表（权限控制）
     *
     * @param queryParams 筛选条件
     * @return IPage<RoleVO> 分页对象
     * @return 查询结果
     */
    IPage<RoleVO> pageRole(RolePageQuery queryParams);

    /**
     * 列表查询角色表（权限控制）
     *
     * @param queryParams 筛选条件
     * @return List<RoleVO> 列表对象
     * @return 查询结果
     */
    List<RoleVO> listRole(RolePageQuery queryParams);


}
