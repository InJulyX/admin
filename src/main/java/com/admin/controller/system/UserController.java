package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.Result;
import com.admin.entity.SysRole;
import com.admin.entity.SysUser;
import com.admin.entity.SysUserRole;
import com.admin.service.SysRoleService;
import com.admin.service.SysUserRoleService;
import com.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/list")
    public Result getList(SysUser sysUser) {
        startPage();
        List<?> list = sysUserService.getSysUserList(sysUser);
        return getResultInfo(list);
    }

    @DeleteMapping(value = "/{userId}")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    public Result remove(@PathVariable Long userId) {
        int result = sysUserService.deleteSysUserById(userId);
        return getResultInfo(result);
    }

    @PostMapping
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    public Result add(@RequestBody SysUser sysUser) {
        return getResultInfo(sysUserService.addSysUser(sysUser));
    }

    @PutMapping
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public Result edit(@RequestBody SysUser sysUser) {
        return getResultInfo(sysUserService.updateSysUser(sysUser));
    }

    @GetMapping(value = {"/", "/{userId}"})
    public Map<String, Object> getInfo(@PathVariable(required = false) Long userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("roles", sysRoleService.getSysRoleAll());
        if (userId != null) {
            result.put("user", sysUserService.getSysUserByUserId(userId));
            result.put("roleIds", sysUserRoleService.getSysRoleIdsByUserId(userId));
        }
        return result;
    }

    @PutMapping(value = "/resetPwd")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public int resetPwd(@RequestBody SysUser sysUser) {
        return sysUserService.updatePassword(sysUser);
    }

}
