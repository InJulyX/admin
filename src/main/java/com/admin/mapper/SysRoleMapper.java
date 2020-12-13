package com.admin.mapper;

import com.admin.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleMapper {
    List<SysRole> getSysRoleList(SysRole sysRole);

    SysRole getSysRole(SysRole sysRole);

    int insert(SysRole sysRole);

    int insertBatch(List<SysRole> sysRoles);

    int update(SysRole sysRole);

    List<SysRole> getSysRoleAll();

    List<Integer> getSysRoleListByUserId(Long userId);
    List<SysRole> getSysRolePermissionByUserId(Long userId);
    int delete(SysRole sysRole);
}
