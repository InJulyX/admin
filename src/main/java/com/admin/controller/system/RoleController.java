package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.SysRole;
import com.admin.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {
    final
    SysRoleService sysRoleService;

    public RoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @RequiresPermissions("system:role:list")
    @GetMapping(value = "/list")
    public TableDataInfo getList(SysRole sysRole) {
        startPage();
        List<?> list = sysRoleService.getSysRoleList(sysRole);
        return getTableData(list);
    }

    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", sysRoleService.getSysRoleById(roleId));
        return ajax;
    }

    @DeleteMapping(value = "/{roleId}")
    public AjaxResult deleteRole(@PathVariable Long roleId) {
        return toAjax(sysRoleService.deleteSysRoleByRoleId(roleId));
    }

    @PostMapping
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    public AjaxResult addRole(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.addSysRole(sysRole));
    }

    @PutMapping
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public AjaxResult editRole(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.updateSysRole(sysRole));
    }
}
