package com.rescue.system.service;

import com.rescue.system.model.entity.StaffProfile;
import com.rescue.system.model.form.StaffProfileForm;
import com.rescue.system.model.vo.StaffProfileVO;
import com.rescue.system.model.query.StaffProfilePageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 员工扩展表（红娘/管理员）表服务接口
 *
 * @author makejava
 * @date 2026-03-05
 */
public interface StaffProfileService extends IService<StaffProfile> {
    /**
     * 新增员工扩展表（红娘/管理员）
     *
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    Long saveStaffProfile(StaffProfileForm formData);

    /**
     * 批量新增员工扩展表（红娘/管理员）
     *
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    Long batchSaveStaffProfile(List<StaffProfileForm> formData);

    /**
     * 更新员工扩展表（红娘/管理员）
     *
     * @param id       主键id
     * @param formData 员工扩展表（红娘/管理员）表单数据
     */
    void updateStaffProfile(Long id, StaffProfileForm formData);

    /**
     * 删除员工扩展表（红娘/管理员）
     *
     * @param idStr 员工扩展表（红娘/管理员）IDs
     */
    void deleteStaffProfile(String idStr);

    /**
     * 通过ID获取员工扩展表（红娘/管理员）
     *
     * @param id 主键
     * @return StaffProfileForm 表单对象
     */
    StaffProfileVO getStaffProfileVo(Long id);

    /**
     * 分页查询员工扩展表（红娘/管理员）
     *
     * @param queryParams 筛选条件
     * @return IPage<StaffProfileVO> 分页对象
     * @return 查询结果
     */
    IPage<StaffProfileVO> pageStaffProfile(StaffProfilePageQuery queryParams);

    /**
     * 列表查询员工扩展表（红娘/管理员）
     *
     * @param queryParams 筛选条件
     * @return List<StaffProfileVO> 列表对象
     * @return 查询结果
     */
    List<StaffProfileVO> listStaffProfile(StaffProfilePageQuery queryParams);


}
