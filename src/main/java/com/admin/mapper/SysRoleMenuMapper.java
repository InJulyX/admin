package com.admin.mapper;

import com.admin.entity.database.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleMenuMapper {
    int deleteSysRoleMenuByRoleId(Long roleId);
    int deleteSysRoleMenuByMenuId(Long roleId);

    int insertBatch(List<SysRoleMenu> sysRoleMenuList);
}
