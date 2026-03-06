package com.rescue.system.controller;

import cn.zhyinfo.common.log.annotaion.Log;
import com.rescue.system.model.form.UserProfileForm;
import com.rescue.system.model.query.UserProfilePageQuery;
import com.rescue.system.model.vo.UserProfileVO;
import com.rescue.system.service.UserProfileService;
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
 * 普通用户扩展表（相亲资料）控制层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Log()
@RestController
@RequiredArgsConstructor
@Tag(name = "普通用户扩展表（相亲资料）接口")
@RequestMapping("/api/v1/userProfile")
public class UserProfileController {

    private final UserProfileService service;

    @Operation(summary = "新增")
    @PostMapping(value = "/add")
    @PreAuthorize("@ss.hasPerm('basicdata:userProfile):add')")
    @PreventDuplicateResubmit
    public Result<Long> saveUserProfile(@Valid @RequestBody UserProfileForm formData) {
        Long id = service.saveUserProfile(formData);
        return Result.success(id);
    }

    @Operation(summary = "修改")
    @PutMapping(value = "/{id}/update")
    @PreAuthorize("@ss.hasPerm('basicdata:userProfile):edit')")
    public Result<Boolean> updateUserProfile(
            @Parameter(description = "主键ID") @PathVariable Long id,
            @Valid @RequestBody UserProfileForm formData) {
        service.updateUserProfile(id, formData);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping(value = "/{ids}/delete")
    @PreAuthorize("@ss.hasPerm('basicdata:userProfile):delete')")
    public Result<Boolean> deleteUserProfile(@Parameter(description = "需要删除的IDs，多个以英文逗号(,)分割") @PathVariable String ids) {
        service.deleteUserProfile(ids);
        return Result.success();
    }

    @Operation(summary = "详情(根据ID获取)")
    @GetMapping("/{id}/form")
    public Result<UserProfileVO> getUserProfileForm(@Parameter(description = "主键ID") @PathVariable Long id) {
        UserProfileVO userProfileVO = service.getUserProfileVo(id);
        return Result.success(userProfileVO);
    }

    @Operation(summary = "分页列表")
    @PostMapping("/page")
    public PageResult<UserProfileVO> pageUserProfile(@RequestBody UserProfilePageQuery queryParams) {
        IPage<UserProfileVO> result = service.pageUserProfile(queryParams);
        return PageResult.success(result);
    }


}

