package com.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserRoleMapper {
    List<Integer> getSysRoleIdsByUserId(Long userId);
}
