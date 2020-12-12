package com.admin.service;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.config.exception.UserExistException;
import com.admin.config.exception.UserNotExistException;
import com.admin.config.shiro.JwtUtil;
import com.admin.constant.Constants;
import com.admin.entity.SysUser;
import com.admin.entity.SysUserRole;
import com.admin.mapper.SysUserMapper;
import com.admin.mapper.SysUserRoleMapper;
import com.admin.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysUserService {
    final
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

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

    public List<SysUser> getSysUserList(SysUser sysUser) {
        return sysUserMapper.getSysUserList(sysUser);
    }

    public int deleteSysUserById(Long userId) {
        return sysUserMapper.deleteSysUserById(userId);
    }

    public boolean isExist(String username) {
        int s1 = sysUserMapper.checkExistByUsername(username);
//        return sysUserMapper.checkExistByUsername(username) == 0;
        log.error("s1 ==> {}", s1);
        return s1 != 0;
    }

    @Transactional
    public int addSysUser(SysUser sysUser) {
        if (!isExist(sysUser.getUsername())) {
            int rows = sysUserMapper.insert(sysUser);
            insertUserRole(sysUser);
            return rows;
        } else {
            throw new UserExistException();
        }
    }

    void insertUserRole(SysUser sysUser) {
        Long[] roles = sysUser.getRoleIds();
        Long userId = sysUser.getId();
        if (StringUtils.isNotNull(roles)) {
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            for (Long roleId : roles) {
                SysUserRole sur = new SysUserRole();
                sur.setUserId(userId);
                sur.setRoleId(roleId);
                sysUserRoleList.add(sur);
            }
            if (sysUserRoleList.size() > 0) {
                sysUserRoleMapper.insertBatch(sysUserRoleList);
            }
        }
    }


    public SysUser getSysUserByUserId(Long userId) {
//        SysUser sysUser = new SysUser();
//        sysUser.setId(userId);
//        return sysUserMapper.getSysUser(sysUser);
        return sysUserMapper.getSysUserByUserId(userId);
    }

    public int updatePassword(Long userId, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(password);
        return sysUserMapper.update(sysUser);
    }

    public int updatePassword(SysUser sysUser) {
        return sysUserMapper.update(sysUser);
    }

    @Transactional
    public int updateSysUser(SysUser sysUser) {
        Long userId = sysUser.getId();
        sysUserRoleMapper.deleteSysUserRoleByUserId(userId);
        insertUserRole(sysUser);
        return sysUserMapper.update(sysUser);
    }
}
