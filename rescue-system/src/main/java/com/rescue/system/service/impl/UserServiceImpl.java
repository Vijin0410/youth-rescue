package com.rescue.system.service.impl;

import com.rescue.system.converter.UserConverter;
import com.rescue.system.model.entity.User;
import com.rescue.system.service.UserService;
import com.rescue.system.model.form.UserForm;
import com.rescue.system.model.query.UserPageQuery;
import com.rescue.system.model.vo.UserVO;
import com.rescue.system.mapper.UserMapper;
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
 * 用户主表（所有角色共有信息）)表服务实现类
 *
 * @author makejava
 * @date 2026-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserConverter converter;

    /**
     * 保存用户主表（所有角色共有信息）
     *
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    @Override
    public Long saveUser(UserForm formData) {
        User entity = converter.formToEntity(formData);
        Assert.isTrue(this.save(entity), "添加失败");
        return entity.getId();
    }

    /**
     * 批量保存用户主表（所有角色共有信息）
     *
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    @Override
    public Long batchSaveUser(List<UserForm> formData) {
        this.saveBatch(converter.formToEntity(formData));
        return 0L;
    }

    /**
     * 更新用户主表（所有角色共有信息）
     *
     * @param id       主键id
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    @Override
    public void updateUser(Long id, UserForm formData) {
        //判断数据是否存在
        getEntity(id);
        User user = converter.formToEntity(formData);
        user.setId(id);
        Assert.isTrue(this.updateById(user), "修改失败");
    }

    /**
     * 删除用户主表（所有角色共有信息）
     *
     * @param idStr 用户主表（所有角色共有信息）IDs
     */
    @Override
    public void deleteUser(String idStr) {
        Assert.isFalse(StringUtils.isEmpty(idStr), "id不能为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong).
                collect(Collectors.toList());
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getDeleted, 1)
                .in(User::getId, ids);
        Assert.isTrue(this.update(wrapper), "删除失败");
    }

    /**
     * 通过ID获取用户主表（所有角色共有信息）
     *
     * @param id 主键
     * @return UserForm 表单对象
     */
    @Override
    public UserVO getUserVo(Long id) {
        User entity = getEntity(id);
        return converter.entityToVo(entity);
    }

    /**
     * 分页查询用户主表（所有角色共有信息）
     *
     * @param queryParams 筛选条件
     * @return IPage<UserVO> 分页对象
     */
    @Override
    public IPage<UserVO> pageUser(UserPageQuery queryParams) {
        // 参数构建
        Page<User> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        //添加查询条件
        Page<User> UserPage = this.baseMapper.selectPage(page, getQueryWrapper(queryParams));
        // 实体转换
        return converter.entityToVOForPage(UserPage);
    }

    @Override
    public List<UserVO> listUser(UserPageQuery queryParams) {
        return converter.entityToVo(this.list(getQueryWrapper(queryParams)));
    }

    private LambdaQueryWrapper<User> getQueryWrapper(UserPageQuery queryParams) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getDeleted, 0);

        qw.orderByDesc(User::getCreateTime);
        return qw;
    }


    private User getEntity(Long id) {
        User entity = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, id)
                .eq(User::getDeleted, 0)
        );
        Assert.isTrue(null != entity, "数据不存在");
        return entity;
    }


}
