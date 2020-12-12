package com.admin.controller;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.constant.Constants;
import com.admin.entity.RouterVO;
import com.admin.entity.SysMenu;
import com.admin.entity.SysUser;
import com.admin.mapper.SysUserMapper;
import com.admin.service.RolePermissionService;
import com.admin.service.SysMenuService;
import com.admin.service.SysRoleService;
import com.admin.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    @Autowired
    RolePermissionService rolePermissionService;

    public LoginController(SysUserService sysUserService, SysUserMapper sysUserMapper, SysMenuService sysMenuService, SysRoleService sysRoleService) {
        this.sysUserService = sysUserService;
        this.sysUserMapper = sysUserMapper;
        this.sysMenuService = sysMenuService;
        this.sysRoleService = sysRoleService;
    }

    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody SysUser sysUser) {
        Map<String, String> result = new HashMap<>();
        String token = sysUserService.login(sysUser);
        result.put("token", token);
        AsyncManager.me().execute(AsyncFactory.insertLoginLog(sysUser.getUsername(), Constants.LOGIN_SUCCESS, "登录成功"));
        return result;
    }

    @PostMapping(value = "/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            SecurityUtils.getSecurityManager().logout(subject);
        }
    }

    @GetMapping(value = "/getInfo")
    public Map<String, Object> getInfo() {
        SysUser sysUser = new SysUser();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        sysUser.setUsername(username);

        SysUser s1 = sysUserMapper.getSysUser(sysUser);
        Map<String, Object> result = new HashMap<>();
        result.put("user", s1);
        result.put("roles", sysRoleService.getSysUserRoleNames(s1));
        result.put("permissions", rolePermissionService.getUserPermissions(s1));
        return result;
    }

    @GetMapping(value = "/getRouters")
    public List<RouterVO> getRouters() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        List<SysMenu> sysMenuList = sysMenuService.selectMenuTreeByUserId(sysUser.getId());
        return sysMenuService.buildMenus(sysMenuList);
    }

    @GetMapping(value = "/captchaImage")
    public Map<String, String> getCode(HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        result.put("uuid", "683c4789995b491b9fab6219297583ff");
        result.put("img", "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");
        return result;
    }

}
