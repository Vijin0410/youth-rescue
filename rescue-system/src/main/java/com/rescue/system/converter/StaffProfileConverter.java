package com.rescue.system.converter;

import com.rescue.system.model.entity.StaffProfile;
import com.rescue.system.model.form.StaffProfileForm;
import com.rescue.system.model.vo.StaffProfileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 员工扩展表（红娘/管理员）对象转换器
 *
 * @author makejava
 * @date 2026-03-05
 */
@Mapper(componentModel = "spring")
public interface StaffProfileConverter {

    StaffProfileForm entityToForm(StaffProfile staffProfile);

    StaffProfileVO entityToVo(StaffProfile staffProfile);

    List<StaffProfileVO> entityToVo(List<StaffProfile> staffProfile);

    StaffProfile formToEntity(StaffProfileForm staffProfileForm);

    List<StaffProfile> formToEntity(List<StaffProfileForm> staffProfileForm);

    Page<StaffProfileVO> entityToVOForPage(Page<StaffProfile> bo);


}
