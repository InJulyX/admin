package com.admin.mapper;

import com.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserMapper {
    SysUser getSysUser(SysUser sysUser);

    List<SysUser> getSysUserList(SysUser sysUser);
    int deleteSysUserById(Long userId);
    int checkExistByUsername(String username);
    int insert(SysUser sysUser);
}
