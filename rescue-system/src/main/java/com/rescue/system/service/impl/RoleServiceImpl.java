package com.rescue.system.service.impl;

import com.rescue.system.converter.RoleConverter;
import com.rescue.system.model.entity.Role;
import com.rescue.system.service.RoleService;
import com.rescue.system.model.form.RoleForm;
import com.rescue.system.model.query.RolePageQuery;
import com.rescue.system.model.vo.RoleVO;
import com.rescue.system.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;

/**
 * 角色表（权限控制）)表服务实现类
 *
 * @author makejava
 * @date 2026-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleConverter converter;

    /**
     * 保存角色表（权限控制）
     *
     * @param formData 角色表（权限控制）表单数据
     */
    @Override
    public Long saveRole(RoleForm formData) {
        Role entity = converter.formToEntity(formData);
        Assert.isTrue(this.save(entity), "添加失败");
        return entity.getId();
    }

    /**
     * 批量保存角色表（权限控制）
     *
     * @param formData 角色表（权限控制）表单数据
     */
    @Override
    public Long batchSaveRole(List<RoleForm> formData) {
        this.saveBatch(converter.formToEntity(formData));
        return 0L;
    }

    /**
     * 更新角色表（权限控制）
     *
     * @param id       主键id
     * @param formData 角色表（权限控制）表单数据
     */
    @Override
    public void updateRole(Long id, RoleForm formData) {
        //判断数据是否存在
        getEntity(id);
        Role role = converter.formToEntity(formData);
        role.setId(id);
        Assert.isTrue(this.updateById(role), "修改失败");
    }

    /**
     * 删除角色表（权限控制）
     *
     * @param idStr 角色表（权限控制）IDs
     */
    @Override
    public void deleteRole(String idStr) {
        Assert.isFalse(StringUtils.isEmpty(idStr), "id不能为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong).
                collect(Collectors.toList());
        LambdaUpdateWrapper<Role> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Role::getDeleted, 1)
                .in(Role::getId, ids);
        Assert.isTrue(this.update(wrapper), "删除失败");
    }

    /**
     * 通过ID获取角色表（权限控制）
     *
     * @param id 主键
     * @return RoleForm 表单对象
     */
    @Override
    public RoleVO getRoleVo(Long id) {
        Role entity = getEntity(id);
        return converter.entityToVo(entity);
    }

    /**
     * 分页查询角色表（权限控制）
     *
     * @param queryParams 筛选条件
     * @return IPage<RoleVO> 分页对象
     */
    @Override
    public IPage<RoleVO> pageRole(RolePageQuery queryParams) {
        // 参数构建
        Page<Role> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        //添加查询条件
        Page<Role> RolePage = this.baseMapper.selectPage(page, getQueryWrapper(queryParams));
        // 实体转换
        return converter.entityToVOForPage(RolePage);
    }

    @Override
    public List<RoleVO> listRole(RolePageQuery queryParams) {
        return converter.entityToVo(this.list(getQueryWrapper(queryParams)));
    }

    private LambdaQueryWrapper<Role> getQueryWrapper(RolePageQuery queryParams) {
        LambdaQueryWrapper<Role> qw = new LambdaQueryWrapper<>();
        qw.eq(Role::getDeleted, 0);

        qw.orderByDesc(Role::getCreateTime);
        return qw;
    }


    private Role getEntity(Long id) {
        Role entity = this.getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getId, id)
                .eq(Role::getDeleted, 0)
        );
        Assert.isTrue(null != entity, "数据不存在");
        return entity;
    }


}
