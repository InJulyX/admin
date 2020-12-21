package com.admin.service;

import com.admin.entity.database.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class RolePermissionService {
    @Autowired
    SysMenuService sysMenuService;

    public Set<String> getUserPermissions(SysUser sysUser) {
        Set<String> perms = new HashSet<>();
        if (sysUser.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(sysMenuService.getUserPermissionsByUserId(sysUser.getId()));
        }
        return perms;
    }
}
