package com.admin.mapper;

import com.admin.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper
@Component
public interface SysUserRoleMapper {
    List<Integer> getSysRoleIdsByUserId(Long userId);
    int insertBatch(List<SysUserRole> sysUserRoleList);
    int deleteSysUserRoleByUserId(Long userId);
    List<String> queryRoleSetByUsername(String username);
    int deleteSysUserRoleByRoleId(Long roleId);
}
