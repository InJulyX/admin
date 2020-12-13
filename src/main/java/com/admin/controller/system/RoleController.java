package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.Result;
import com.admin.entity.SysRole;
import com.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/list")
    public Result getList(SysRole sysRole) {
        startPage();
        List<?> list = sysRoleService.getSysRoleList(sysRole);
        return getResultInfo(list);
    }

    @GetMapping(value = "/{roleId}")
    public SysRole getInfo(@PathVariable Long roleId) {
        return sysRoleService.getSysRoleById(roleId);
    }

    @DeleteMapping(value = "/{roleId}")
    public Result deleteRole(@PathVariable Long roleId) {
        return getResultInfo(sysRoleService.deleteSysRoleByRoleId(roleId));
    }

    @PostMapping
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    public void addRole(@RequestBody SysRole sysRole) {
        sysRoleService.addSysRole(sysRole);
    }

    @PutMapping
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public void editRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
    }
}
