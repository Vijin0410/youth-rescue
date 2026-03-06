package com.rescue.system.controller;

import cn.zhyinfo.common.log.annotaion.Log;
import com.rescue.system.model.form.UserForm;
import com.rescue.system.model.query.UserPageQuery;
import com.rescue.system.model.vo.UserVO;
import com.rescue.system.service.UserService;
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
 * 用户主表（所有角色共有信息）控制层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Log()
@RestController
@RequiredArgsConstructor
@Tag(name = "用户主表（所有角色共有信息）接口")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;

    @Operation(summary = "新增")
    @PostMapping(value = "/add")
    @PreAuthorize("@ss.hasPerm('basicdata:user):add')")
    @PreventDuplicateResubmit
    public Result<Long> saveUser(@Valid @RequestBody UserForm formData) {
        Long id = service.saveUser(formData);
        return Result.success(id);
    }

    @Operation(summary = "修改")
    @PutMapping(value = "/{id}/update")
    @PreAuthorize("@ss.hasPerm('basicdata:user):edit')")
    public Result<Boolean> updateUser(
            @Parameter(description = "主键ID") @PathVariable Long id,
            @Valid @RequestBody UserForm formData) {
        service.updateUser(id, formData);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping(value = "/{ids}/delete")
    @PreAuthorize("@ss.hasPerm('basicdata:user):delete')")
    public Result<Boolean> deleteUser(@Parameter(description = "需要删除的IDs，多个以英文逗号(,)分割") @PathVariable String ids) {
        service.deleteUser(ids);
        return Result.success();
    }

    @Operation(summary = "详情(根据ID获取)")
    @GetMapping("/{id}/form")
    public Result<UserVO> getUserForm(@Parameter(description = "主键ID") @PathVariable Long id) {
        UserVO userVO = service.getUserVo(id);
        return Result.success(userVO);
    }

    @Operation(summary = "分页列表")
    @PostMapping("/page")
    public PageResult<UserVO> pageUser(@RequestBody UserPageQuery queryParams) {
        IPage<UserVO> result = service.pageUser(queryParams);
        return PageResult.success(result);
    }


}

