package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.Result;
import com.admin.entity.SysMenu;
import com.admin.entity.SysUser;
import com.admin.entity.TreeSelect;
import com.admin.service.SysMenuService;
import com.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysMenuService sysMenuService;

    @GetMapping(value = "/list")
    public List<SysMenu> list(SysMenu sysMenu) {
//        String username = getUsername();
//        SysUser sysUser = sysUserService.getSysUserByUsername(username);
//        List<SysMenu> sysMenuList =sysMenuService.
        return sysMenuService.getSysMenuList(sysMenu);
    }

    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public void add(@RequestBody SysMenu sysMenu) {
        sysMenuService.insertMenu(sysMenu);
    }

    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/{menuId}")
    public void delete(@PathVariable("menuId") Long menuId) {
        sysMenuService.deleteByMenuId(menuId);
    }

    @GetMapping(value = "/treeSelect")
    public List<TreeSelect> getTreeSelect(SysMenu sysMenu) {
        String username = getUsername();
        Long userId = sysUserService.getSysUserByUsername(username).getId();
        List<SysMenu> sysMenuList = sysMenuService.getSysMenuList(sysMenu, userId);
        return sysMenuService.buildSysMenuTreeSelect(sysMenuList);

    }

    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public Map<String, Object> roleMenuTreeSelect(@PathVariable Long roleId) {
        String username = getUsername();
        Long userId = sysUserService.getSysUserByUsername(username).getId();
        List<SysMenu> sysMenuList = sysMenuService.getSysMenuList(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("checkedKeys", sysMenuService.getSysMenuListByRoleId(roleId));
        result.put("menus", sysMenuService.buildSysMenuTreeSelect(sysMenuList));
        return result;
    }
}
