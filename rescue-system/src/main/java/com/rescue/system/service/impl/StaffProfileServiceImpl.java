package com.rescue.system.service.impl;

import com.rescue.system.converter.StaffProfileConverter;
import com.rescue.system.model.entity.StaffProfile;
import com.rescue.system.service.StaffProfileService;
import com.rescue.system.model.form.StaffProfileForm;
import com.rescue.system.model.query.StaffProfilePageQuery;
import com.rescue.system.model.vo.StaffProfileVO;
import com.rescue.system.mapper.StaffProfileMapper;
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
 * 员工扩展表（红娘/管理员）)表服务实现类
 *
 * @author makejava
 * @date 2026-03-05
 */
@Service
public class StaffProfileServiceImpl extends ServiceImpl<StaffProfileMapper, StaffProfile> implements StaffProfileService {
    @Resource
    private StaffProfileConverter converter;

    /**
     * 保存员工扩展表（红娘/管理员）
     *
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    @Override
    public Long saveStaffProfile(StaffProfileForm formData) {
        StaffProfile entity = converter.formToEntity(formData);
        Assert.isTrue(this.save(entity), "添加失败");
        return entity.getId();
    }

    /**
     * 批量保存员工扩展表（红娘/管理员）
     *
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    @Override
    public Long batchSaveStaffProfile(List<StaffProfileForm> formData) {
        this.saveBatch(converter.formToEntity(formData));
        return 0L;
    }

    /**
     * 更新员工扩展表（红娘/管理员）
     *
     * @param id       主键id
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    @Override
    public void updateStaffProfile(Long id, StaffProfileForm formData) {
        //判断数据是否存在
        getEntity(id);
        StaffProfile staffProfile = converter.formToEntity(formData);
        staffProfile.setId(id);
        Assert.isTrue(this.updateById(staffProfile), "修改失败");
    }

    /**
     * 删除员工扩展表（红娘/管理员）
     *
     * @param idStr 员工扩展表（红娘/管理员）IDs
     */
    @Override
    public void deleteStaffProfile(String idStr) {
        Assert.isFalse(StringUtils.isEmpty(idStr), "id不能为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong).
                collect(Collectors.toList());
        LambdaUpdateWrapper<StaffProfile> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(StaffProfile::getDeleted, 1)
                .in(StaffProfile::getId, ids);
        Assert.isTrue(this.update(wrapper), "删除失败");
    }

    /**
     * 通过ID获取员工扩展表（红娘/管理员）
     *
     * @param id 主键
     * @return StaffProfileForm 表单对象
     */
    @Override
    public StaffProfileVO getStaffProfileVo(Long id) {
        StaffProfile entity = getEntity(id);
        return converter.entityToVo(entity);
    }

    /**
     * 分页查询员工扩展表（红娘/管理员）
     *
     * @param queryParams 筛选条件
     * @return IPage<StaffProfileVO> 分页对象
     */
    @Override
    public IPage<StaffProfileVO> pageStaffProfile(StaffProfilePageQuery queryParams) {
        // 参数构建
        Page<StaffProfile> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        //添加查询条件
        Page<StaffProfile> StaffProfilePage = this.baseMapper.selectPage(page, getQueryWrapper(queryParams));
        // 实体转换
        return converter.entityToVOForPage(StaffProfilePage);
    }

    @Override
    public List<StaffProfileVO> listStaffProfile(StaffProfilePageQuery queryParams) {
        return converter.entityToVo(this.list(getQueryWrapper(queryParams)));
    }

    private LambdaQueryWrapper<StaffProfile> getQueryWrapper(StaffProfilePageQuery queryParams) {
        LambdaQueryWrapper<StaffProfile> qw = new LambdaQueryWrapper<>();
        qw.eq(StaffProfile::getDeleted, 0);

        qw.orderByDesc(StaffProfile::getCreateTime);
        return qw;
    }


    private StaffProfile getEntity(Long id) {
        StaffProfile entity = this.getOne(new LambdaQueryWrapper<StaffProfile>()
                .eq(StaffProfile::getId, id)
                .eq(StaffProfile::getDeleted, 0)
        );
        Assert.isTrue(null != entity, "数据不存在");
        return entity;
    }


}
