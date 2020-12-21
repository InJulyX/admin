package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.SysMenu;
import com.admin.service.SysMenuService;
import com.admin.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
    final
    SysUserService sysUserService;
    final
    SysMenuService sysMenuService;

    public MenuController(SysUserService sysUserService, SysMenuService sysMenuService) {
        this.sysUserService = sysUserService;
        this.sysMenuService = sysMenuService;
    }

    @GetMapping(value = "/list")
    public TableDataInfo list(SysMenu sysMenu) {
        List<?> list = sysMenuService.getSysMenuList(sysMenu);
        return getTableData(list);
    }

    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreateBy(getUsername());
        return toAjax(sysMenuService.insertMenu(sysMenu));
    }

    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/{menuId}")
    public AjaxResult delete(@PathVariable("menuId") Long menuId) {
        return toAjax(sysMenuService.deleteByMenuId(menuId));
    }

    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateBy(getUsername());
        return toAjax(sysMenuService.updateSysMenu(sysMenu));
    }


    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", sysMenuService.getSysMenuByMenuId(menuId));
        return ajax;
    }

    @GetMapping(value = "/treeSelect")
    public AjaxResult getTreeSelect(SysMenu sysMenu) {
        String username = getUsername();
        Long userId = sysUserService.getSysUserByUsername(username).getId();
        List<SysMenu> sysMenuList = sysMenuService.getSysMenuList(sysMenu, userId);
        AjaxResult ajax = AjaxResult.success();
        List<?> list = sysMenuService.buildSysMenuTreeSelect(sysMenuList);
        ajax.put("data", list);
        return ajax;

    }

    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public AjaxResult roleMenuTreeSelect(@PathVariable Long roleId) {
        String username = getUsername();
        Long userId = sysUserService.getSysUserByUsername(username).getId();
        List<SysMenu> sysMenuList = sysMenuService.getSysMenuList(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("checkedKeys", sysMenuService.getSysMenuListByRoleId(roleId));
        result.put("menus", sysMenuService.buildSysMenuTreeSelect(sysMenuList));
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", result);
        return ajax;
    }
}
