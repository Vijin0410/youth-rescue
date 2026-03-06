package com.rescue.system.controller;

import cn.zhyinfo.common.log.annotaion.Log;
import com.rescue.system.model.form.StaffProfileForm;
import com.rescue.system.model.query.StaffProfilePageQuery;
import com.rescue.system.model.vo.StaffProfileVO;
import com.rescue.system.service.StaffProfileService;
import cn.zhyinfo.common.result.Result;
import cn.zhyinfo.common.result.PageResult;
import cn.zhyinfo.common.web.annotation.PreventDuplicateResubmit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工扩展表（红娘/管理员）控制层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Log()
@RestController
@RequiredArgsConstructor
@Tag(name = "员工扩展表（红娘/管理员）接口")
@RequestMapping("/api/v1/staffProfile")
public class StaffProfileController {

    private final StaffProfileService service;

    @Operation(summary = "新增")
    @PostMapping(value = "/add")
    @PreAuthorize("@ss.hasPerm('basicdata:staffProfile):add')")
    @PreventDuplicateResubmit
    public Result<Long> saveStaffProfile(@Valid @RequestBody StaffProfileForm formData) {
        Long id = service.saveStaffProfile(formData);
        return Result.success(id);
    }

    @Operation(summary = "修改")
    @PutMapping(value = "/{id}/update")
    @PreAuthorize("@ss.hasPerm('basicdata:staffProfile):edit')")
    public Result<Boolean> updateStaffProfile(
            @Parameter(description = "主键ID") @PathVariable Long id,
            @Valid @RequestBody StaffProfileForm formData) {
        service.updateStaffProfile(id, formData);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping(value = "/{ids}/delete")
    @PreAuthorize("@ss.hasPerm('basicdata:staffProfile):delete')")
    public Result<Boolean> deleteStaffProfile(@Parameter(description = "需要删除的IDs，多个以英文逗号(,)分割") @PathVariable String ids) {
        service.deleteStaffProfile(ids);
        return Result.success();
    }

    @Operation(summary = "详情(根据ID获取)")
    @GetMapping("/{id}/form")
    public Result<StaffProfileVO> getStaffProfileForm(@Parameter(description = "主键ID") @PathVariable Long id) {
        StaffProfileVO staffProfileVO = service.getStaffProfileVo(id);
        return Result.success(staffProfileVO);
    }

    @Operation(summary = "分页列表")
    @PostMapping("/page")
    public PageResult<StaffProfileVO> pageStaffProfile(@RequestBody StaffProfilePageQuery queryParams) {
        IPage<StaffProfileVO> result = service.pageStaffProfile(queryParams);
        return PageResult.success(result);
    }


}

