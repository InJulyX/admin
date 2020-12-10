package com.admin.mapper;

import com.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserMapper {
    SysUser getSysUser(SysUser sysUser);
}
