package com.admin.mapper;

import com.admin.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleMenuMapper {
    int deleteSysRoleMenuByRoleId(Long roleId);

    int insertBatch(List<SysRoleMenu> sysRoleMenuList);
}
