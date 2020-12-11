package com.admin.service;

import com.admin.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public List<Integer> getSysRoleIdsByUserId(Long userId) {
        return sysUserRoleMapper.getSysRoleIdsByUserId(userId);
    }
}
