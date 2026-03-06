package com.rescue.system.converter;

import com.rescue.system.model.entity.User;
import com.rescue.system.model.form.UserForm;
import com.rescue.system.model.vo.UserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 用户主表（所有角色共有信息）对象转换器
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserForm entityToForm(User user);

    UserVO entityToVo(User user);

    List<UserVO> entityToVo(List<User> user);

    User formToEntity(UserForm userForm);

    List<User> formToEntity(List<UserForm> userForm);

    Page<UserVO> entityToVOForPage(Page<User> bo);


}
