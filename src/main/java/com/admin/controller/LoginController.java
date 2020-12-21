package com.admin.controller;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.constant.Constants;
import com.admin.entity.AjaxResult;
import com.admin.entity.database.SysMenu;
import com.admin.entity.database.SysUser;
import com.admin.mapper.SysUserMapper;
import com.admin.service.RolePermissionService;
import com.admin.service.SysMenuService;
import com.admin.service.SysRoleService;
import com.admin.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    final
    SysUserService sysUserService;
    final
    SysUserMapper sysUserMapper;
    final
    SysMenuService sysMenuService;
    final
    SysRoleService sysRoleService;
    final
    RolePermissionService rolePermissionService;

    public LoginController(SysUserService sysUserService, SysUserMapper sysUserMapper, SysMenuService sysMenuService, SysRoleService sysRoleService, RolePermissionService rolePermissionService) {
        this.sysUserService = sysUserService;
        this.sysUserMapper = sysUserMapper;
        this.sysMenuService = sysMenuService;
        this.sysRoleService = sysRoleService;
        this.rolePermissionService = rolePermissionService;
    }


    @PostMapping(value = "/login")
    public AjaxResult login(@RequestBody SysUser sysUser) {
        AjaxResult ajax = AjaxResult.success();
        String token = sysUserService.login(sysUser);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        ajax.put("data", data);
        AsyncManager.me().execute(AsyncFactory.insertLoginLog(sysUser.getUsername(), Constants.LOGIN_SUCCESS, "登录成功"));
        return ajax;
    }

    @PostMapping(value = "/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            SecurityUtils.getSecurityManager().logout(subject);
        }
    }

    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo() {
        SysUser sysUser = new SysUser();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        sysUser.setUsername(username);

        SysUser s1 = sysUserMapper.getSysUser(sysUser);
        Map<String, Object> result = new HashMap<>();
        result.put("user", s1);
        result.put("roles", sysRoleService.getSysUserRoleNames(s1));
        result.put("permissions", rolePermissionService.getUserPermissions(s1));
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", result);
        return ajaxResult;
    }

    @GetMapping(value = "/getRouters")
    public AjaxResult getRouters() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        List<SysMenu> sysMenuList = sysMenuService.selectMenuTreeByUserId(sysUser.getId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", sysMenuService.buildMenus(sysMenuList));
        return ajax;
    }

}
