package com.admin.service;

import com.admin.entity.SysRole;
import com.admin.entity.SysRoleMenu;
import com.admin.mapper.SysRoleMapper;
import com.admin.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService {
    final
    SysRoleMapper sysRoleMapper;
    final
    SysRoleMenuMapper sysRoleMenuMapper;


    public SysRoleService(SysRoleMapper sysRoleMapper, SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    public List<SysRole> getSysRoleList(SysRole sysRole) {
        return sysRoleMapper.getSysRoleList(sysRole);
    }

    public SysRole getSysRoleById(Long roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        return sysRoleMapper.getSysRole(sysRole);
    }

    @Transactional
    public int addSysRole(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Transactional
    public int updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
        sysRoleMenuMapper.deleteSysRoleMenuByRoleId(sysRole.getId());
        return insertSysRoleMenu(sysRole);
    }

    public int insertSysRoleMenu(SysRole sysRole) {
        int rows = 1;
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        Long roleId = sysRole.getId();
        for (Long menuId : sysRole.getMenuIds()) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenuList.add(sysRoleMenu);
        }
        if (sysRoleMenuList.size() > 0) {
            rows = sysRoleMenuMapper.insertBatch(sysRoleMenuList);
        }
        return rows;
    }

    public List<SysRole> getSysRoleAll() {
        return sysRoleMapper.getSysRoleAll();
    }

    public List<Integer> getSysRoleListByUserId(Long userId) {
        return sysRoleMapper.getSysRoleListByUserId(userId);
    }
}
