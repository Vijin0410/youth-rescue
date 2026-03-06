package com.rescue.system.converter;

import com.rescue.system.model.entity.UserProfile;
import com.rescue.system.model.form.UserProfileForm;
import com.rescue.system.model.vo.UserProfileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 普通用户扩展表（相亲资料）对象转换器
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper(componentModel = "spring")
public interface UserProfileConverter {

    UserProfileForm entityToForm(UserProfile userProfile);

    UserProfileVO entityToVo(UserProfile userProfile);

    List<UserProfileVO> entityToVo(List<UserProfile> userProfile);

    UserProfile formToEntity(UserProfileForm userProfileForm);

    List<UserProfile> formToEntity(List<UserProfileForm> userProfileForm);

    Page<UserProfileVO> entityToVOForPage(Page<UserProfile> bo);


}
