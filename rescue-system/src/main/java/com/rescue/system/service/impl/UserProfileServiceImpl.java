package com.rescue.system.service.impl;

import com.rescue.system.converter.UserProfileConverter;
import com.rescue.system.model.entity.UserProfile;
import com.rescue.system.service.UserProfileService;
import com.rescue.system.model.form.UserProfileForm;
import com.rescue.system.model.query.UserProfilePageQuery;
import com.rescue.system.model.vo.UserProfileVO;
import com.rescue.system.mapper.UserProfileMapper;
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
 * 普通用户扩展表（相亲资料）)表服务实现类
 *
 * @author makejava
 * @date 2026-03-05
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {
    @Resource
    private UserProfileConverter converter;

    /**
     * 保存普通用户扩展表（相亲资料）
     *
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    @Override
    public Long saveUserProfile(UserProfileForm formData) {
        UserProfile entity = converter.formToEntity(formData);
        Assert.isTrue(this.save(entity), "添加失败");
        return entity.getId();
    }

    /**
     * 批量保存普通用户扩展表（相亲资料）
     *
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    @Override
    public Long batchSaveUserProfile(List<UserProfileForm> formData) {
        this.saveBatch(converter.formToEntity(formData));
        return 0L;
    }

    /**
     * 更新普通用户扩展表（相亲资料）
     *
     * @param id       主键id
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    @Override
    public void updateUserProfile(Long id, UserProfileForm formData) {
        //判断数据是否存在
        getEntity(id);
        UserProfile userProfile = converter.formToEntity(formData);
        userProfile.setId(id);
        Assert.isTrue(this.updateById(userProfile), "修改失败");
    }

    /**
     * 删除普通用户扩展表（相亲资料）
     *
     * @param idStr 普通用户扩展表（相亲资料）IDs
     */
    @Override
    public void deleteUserProfile(String idStr) {
        Assert.isFalse(StringUtils.isEmpty(idStr), "id不能为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong).
                collect(Collectors.toList());
        LambdaUpdateWrapper<UserProfile> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserProfile::getDeleted, 1)
                .in(UserProfile::getId, ids);
        Assert.isTrue(this.update(wrapper), "删除失败");
    }

    /**
     * 通过ID获取普通用户扩展表（相亲资料）
     *
     * @param id 主键
     * @return UserProfileForm 表单对象
     */
    @Override
    public UserProfileVO getUserProfileVo(Long id) {
        UserProfile entity = getEntity(id);
        return converter.entityToVo(entity);
    }

    /**
     * 分页查询普通用户扩展表（相亲资料）
     *
     * @param queryParams 筛选条件
     * @return IPage<UserProfileVO> 分页对象
     */
    @Override
    public IPage<UserProfileVO> pageUserProfile(UserProfilePageQuery queryParams) {
        // 参数构建
        Page<UserProfile> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        //添加查询条件
        Page<UserProfile> UserProfilePage = this.baseMapper.selectPage(page, getQueryWrapper(queryParams));
        // 实体转换
        return converter.entityToVOForPage(UserProfilePage);
    }

    @Override
    public List<UserProfileVO> listUserProfile(UserProfilePageQuery queryParams) {
        return converter.entityToVo(this.list(getQueryWrapper(queryParams)));
    }

    private LambdaQueryWrapper<UserProfile> getQueryWrapper(UserProfilePageQuery queryParams) {
        LambdaQueryWrapper<UserProfile> qw = new LambdaQueryWrapper<>();
        qw.eq(UserProfile::getDeleted, 0);

        qw.orderByDesc(UserProfile::getCreateTime);
        return qw;
    }


    private UserProfile getEntity(Long id) {
        UserProfile entity = this.getOne(new LambdaQueryWrapper<UserProfile>()
                .eq(UserProfile::getId, id)
                .eq(UserProfile::getDeleted, 0)
        );
        Assert.isTrue(null != entity, "数据不存在");
        return entity;
    }


}
