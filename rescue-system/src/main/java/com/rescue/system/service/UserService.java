package com.rescue.system.service;

import com.rescue.system.model.entity.User;
import com.rescue.system.model.form.UserForm;
import com.rescue.system.model.vo.UserVO;
import com.rescue.system.model.query.UserPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户主表（所有角色共有信息）表服务接口
 *
 * @author makejava
 * @date 2026-03-05
 */
public interface UserService extends IService<User> {
    /**
     * 新增用户主表（所有角色共有信息）
     *
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    Long saveUser(UserForm formData);

    /**
     * 批量新增用户主表（所有角色共有信息）
     *
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    Long batchSaveUser(List<UserForm> formData);

    /**
     * 更新用户主表（所有角色共有信息）
     *
     * @param id       主键id
     * @param formData 用户主表（所有角色共有信息）表单数据
     */
    void updateUser(Long id, UserForm formData);

    /**
     * 删除用户主表（所有角色共有信息）
     *
     * @param idStr 用户主表（所有角色共有信息）IDs
     */
    void deleteUser(String idStr);

    /**
     * 通过ID获取用户主表（所有角色共有信息）
     *
     * @param id 主键
     * @return UserForm 表单对象
     */
    UserVO getUserVo(Long id);

    /**
     * 分页查询用户主表（所有角色共有信息）
     *
     * @param queryParams 筛选条件
     * @return IPage<UserVO> 分页对象
     * @return 查询结果
     */
    IPage<UserVO> pageUser(UserPageQuery queryParams);

    /**
     * 列表查询用户主表（所有角色共有信息）
     *
     * @param queryParams 筛选条件
     * @return List<UserVO> 列表对象
     * @return 查询结果
     */
    List<UserVO> listUser(UserPageQuery queryParams);


}
