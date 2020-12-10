package com.admin.service;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.config.exception.UserNotExistException;
import com.admin.config.shiro.JwtUtil;
import com.admin.constant.Constants;
import com.admin.entity.SysUser;
import com.admin.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class SysUserService {
    final
    SysUserMapper sysUserMapper;

    public SysUserService(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    public Set<String> queryRoles(String username) {
        log.debug("com.admin.serviceSysUserService::queryRoles");
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("root");
        return roles;
    }

    public Set<String> queryPermissions(String username) {
        return null;
    }

    public String login(SysUser sysUser) {
        SysUser user = sysUserMapper.getSysUser(sysUser);
        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.insertLoginLog(sysUser.getUsername(), Constants.LOGIN_FAIL, "用户名或密码错误"));
            throw new UserNotExistException();
        }
        return JwtUtil.sign(user.getUsername(), String.valueOf(System.currentTimeMillis()));
    }

    public SysUser getSysUserByUsername(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return sysUserMapper.getSysUser(sysUser);
    }
}
