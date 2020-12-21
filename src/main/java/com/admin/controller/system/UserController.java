package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.SysUser;
import com.admin.service.SysRoleService;
import com.admin.service.SysUserRoleService;
import com.admin.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {
    final
    SysUserService sysUserService;
    final
    SysRoleService sysRoleService;
    final
    SysUserRoleService sysUserRoleService;

    public UserController(SysUserService sysUserService, SysRoleService sysRoleService, SysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
        this.sysUserRoleService = sysUserRoleService;
    }

    @RequiresPermissions("system:user:list")
    @GetMapping(value = "/list")
    public TableDataInfo getList(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.getSysUserList(sysUser);
        return getTableData(list);
    }

    @RequiresPermissions("system:user:delete")
    @DeleteMapping(value = "/{userId}")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long userId) {
        return toAjax(sysUserService.deleteSysUserById(userId));
    }

    @RequiresPermissions("system:user:add")
    @PostMapping
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    public AjaxResult add(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.addSysUser(sysUser));
    }

    @RequiresPermissions("system:user:edit")
    @PutMapping
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateSysUser(sysUser));
    }

    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(required = false) Long userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("roles", sysRoleService.getSysRoleAll());
        if (userId != null) {
            result.put("user", sysUserService.getSysUserByUserId(userId));
            result.put("roleIds", sysUserRoleService.getSysRoleIdsByUserId(userId));
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", result);
        return ajax;
    }

    @RequiresPermissions("system:user:edit")
    @PutMapping(value = "/resetPwd")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public AjaxResult resetPwd(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updatePassword(sysUser));
    }

    @RequiresPermissions("system:user:edit")
    @PutMapping(value = "/changeStatus")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public AjaxResult changeStatus(@RequestBody SysUser sysUser) {
        sysUser.setUpdateBy(getUsername());
        return toAjax(sysUserService.updateSysUserStatus(sysUser));
    }

}
