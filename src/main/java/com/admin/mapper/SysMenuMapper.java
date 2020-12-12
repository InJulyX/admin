package com.admin.mapper;

import com.admin.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysMenuMapper {
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> getSysMenuList(SysMenu sysMenu);

    int insert(SysMenu sysMenu);

    int deleteByMenuId(Long menuId);

    List<SysMenu> getSysMenuListByUserId(SysMenu sysMenu);

    List<Integer> getSysMenuListByRoleId(Long roleId, boolean menuCheckStrictly);

    List<String> getSysUserPermissionsByUserId(Long userId);
}
