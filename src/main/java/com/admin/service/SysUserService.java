package com.admin.service;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.config.exception.UserExistException;
import com.admin.config.exception.UserNotExistException;
import com.admin.config.shiro.JwtUtil;
import com.admin.constant.Constants;
import com.admin.entity.database.SysUser;
import com.admin.entity.database.SysUserRole;
import com.admin.mapper.SysMenuMapper;
import com.admin.mapper.SysUserMapper;
import com.admin.mapper.SysUserRoleMapper;
import com.admin.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
public class SysUserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        logger.info("sysUserServiceBean 初始化完成");
    }

    final
    SysUserMapper sysUserMapper;
    final
    SysUserRoleMapper sysUserRoleMapper;
    final SysMenuMapper sysMenuMapper;
    final SysMenuService sysMenuService;


    public SysUserService(SysUserMapper sysUserMapper, SysUserRoleMapper sysUserRoleMapper, SysMenuMapper sysMenuMapper, SysMenuService sysMenuService) {
        this.sysUserMapper = sysUserMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysMenuService = sysMenuService;
    }

    public Set<String> queryRoles(String username) {
        if (SysUser.isAdmin(username)) {
            Set<String> roles = new HashSet<>();
            roles.add("system");
            return roles;
        }
        List<String> stringList = sysUserRoleMapper.queryRoleSetByUsername(username);
        Set<String> rolesSet = new HashSet<>();
        for (String role : stringList) {
            if (StringUtils.isNotEmpty(role)) {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return rolesSet;
    }

    public Set<String> queryPermissions(String username) {
        Set<String> perms = new HashSet<>();

        if (SysUser.isAdmin(username)) {
            perms.add("*:*:*");
        } else {
            SysUser sysUser = getSysUserByUsername(username);
            perms = sysMenuService.getUserPermissionsByUserId(sysUser.getId());
        }
        return perms;
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
        sysUser.setId(sysUserMapper.getSysUser(sysUser).getId());
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

    public int updateSysUserStatus(SysUser sysUser) {
        return sysUserMapper.update(sysUser);
    }
}
