package com.rescue.system.controller;


import com.rescue.common.result.PageResult;
import com.rescue.common.result.Result;
import com.rescue.framework.annotation.PreventDuplicateResubmit;
import com.rescue.system.model.form.RoleForm;
import com.rescue.system.model.query.RolePageQuery;
import com.rescue.system.model.vo.RoleVO;
import com.rescue.system.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 角色表（权限控制）控制层
 *
 * @author makejava
 * @date 2026-03-05
 */
@Log()
@RestController
@RequiredArgsConstructor
@Tag(name = "角色表（权限控制）接口")
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService service;

    @Operation(summary = "新增")
    @PostMapping(value = "/add")
    @PreAuthorize("@ss.hasPerm('basicdata:role):add')")
    @PreventDuplicateResubmit
    public Result<Long> saveRole(@Valid @RequestBody RoleForm formData) {
        Long id = service.saveRole(formData);
        return Result.success(id);
    }

    @Operation(summary = "修改")
    @PutMapping(value = "/{id}/update")
    @PreAuthorize("@ss.hasPerm('basicdata:role):edit')")
    public Result<Boolean> updateRole(
            @Parameter(description = "主键ID") @PathVariable Long id,
            @Valid @RequestBody RoleForm formData) {
        service.updateRole(id, formData);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping(value = "/{ids}/delete")
    @PreAuthorize("@ss.hasPerm('basicdata:role):delete')")
    public Result<Boolean> deleteRole(@Parameter(description = "需要删除的IDs，多个以英文逗号(,)分割") @PathVariable String ids) {
        service.deleteRole(ids);
        return Result.success();
    }

    @Operation(summary = "详情(根据ID获取)")
    @GetMapping("/{id}/form")
    public Result<RoleVO> getRoleForm(@Parameter(description = "主键ID") @PathVariable Long id) {
        RoleVO roleVO = service.getRoleVo(id);
        return Result.success(roleVO);
    }

    @Operation(summary = "分页列表")
    @PostMapping("/page")
    public PageResult<RoleVO> pageRole(@RequestBody RolePageQuery queryParams) {
        IPage<RoleVO> result = service.pageRole(queryParams);
        return PageResult.success(result);
    }


}

