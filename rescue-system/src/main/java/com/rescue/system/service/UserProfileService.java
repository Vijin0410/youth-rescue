package com.rescue.system.service;

import com.rescue.system.model.entity.UserProfile;
import com.rescue.system.model.form.UserProfileForm;
import com.rescue.system.model.vo.UserProfileVO;
import com.rescue.system.model.query.UserProfilePageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 普通用户扩展表（相亲资料）表服务接口
 *
 * @author makejava
 * @date 2026-03-05
 */
public interface UserProfileService extends IService<UserProfile> {
    /**
     * 新增普通用户扩展表（相亲资料）
     *
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    Long saveUserProfile(UserProfileForm formData);

    /**
     * 批量新增普通用户扩展表（相亲资料）
     *
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    Long batchSaveUserProfile(List<UserProfileForm> formData);

    /**
     * 更新普通用户扩展表（相亲资料）
     *
     * @param id       主键id
     * @param formData 普通用户扩展表（相亲资料）表单数据
     */
    void updateUserProfile(Long id, UserProfileForm formData);

    /**
     * 删除普通用户扩展表（相亲资料）
     *
     * @param idStr 普通用户扩展表（相亲资料）IDs
     */
    void deleteUserProfile(String idStr);

    /**
     * 通过ID获取普通用户扩展表（相亲资料）
     *
     * @param id 主键
     * @return UserProfileForm 表单对象
     */
    UserProfileVO getUserProfileVo(Long id);

    /**
     * 分页查询普通用户扩展表（相亲资料）
     *
     * @param queryParams 筛选条件
     * @return IPage<UserProfileVO> 分页对象
     * @return 查询结果
     */
    IPage<UserProfileVO> pageUserProfile(UserProfilePageQuery queryParams);

    /**
     * 列表查询普通用户扩展表（相亲资料）
     *
     * @param queryParams 筛选条件
     * @return List<UserProfileVO> 列表对象
     * @return 查询结果
     */
    List<UserProfileVO> listUserProfile(UserProfilePageQuery queryParams);


}
