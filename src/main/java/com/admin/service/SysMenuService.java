package com.admin.service;

import com.admin.entity.*;
import com.admin.mapper.SysMenuMapper;
import com.admin.mapper.SysRoleMapper;
import com.admin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysMenuService {
    final
    SysMenuMapper sysMenuMapper;
    final
    SysRoleMapper sysRoleMapper;

    public SysMenuService(SysMenuMapper sysMenuMapper, SysRoleMapper sysRoleMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menuList = new ArrayList<>();
        if (SysUser.isAdmin(userId)) {
            menuList = sysMenuMapper.selectMenuTreeAll();
        } else {
            menuList = sysMenuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menuList, 0L);
    }

    public List<SysMenu> getChildPerms(List<SysMenu> list, long parentId) {
        List<SysMenu> sysMenuList = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            if (sysMenu.getParentId() == parentId) {
                recursionFn(list, sysMenu);
                sysMenuList.add(sysMenu);
            }
        }
        return sysMenuList;
    }

    public void recursionFn(List<SysMenu> sysMenuList, SysMenu sysMenu) {
        List<SysMenu> childList = getChildList(sysMenuList, sysMenu);
        sysMenu.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(sysMenuList, tChild)) {
                recursionFn(sysMenuList, tChild);
            }
        }
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu sysMenu) {
        List<SysMenu> sysMenuList = new ArrayList<>();
        for (SysMenu _sysMenu : list) {
            if (_sysMenu.getParentId().longValue() == sysMenu.getMenuId().longValue()) {
                sysMenuList.add(_sysMenu);
            }
        }
        return sysMenuList;
    }

    private boolean hasChild(List<SysMenu> sysMenuList, SysMenu sysMenu) {
        return getChildList(sysMenuList, sysMenu).size() > 0;
    }

    public List<RouterVO> buildMenus(List<SysMenu> sysMenuList) {
        List<RouterVO> routers = new LinkedList<>();
        for (SysMenu sysMenu : sysMenuList) {
            RouterVO routerVO = new RouterVO();
            routerVO.setHidden("1".equals(sysMenu.getVisible()));
            routerVO.setName(getRouteName(sysMenu));
            routerVO.setPath(getRouterPath(sysMenu));
            routerVO.setComponent(getComponent(sysMenu));
            routerVO.setMeta(new MetaVO(sysMenu.getMenuName(), sysMenu.getIcon(), StringUtils.equals("1", sysMenu.getIsCache())));
            List<SysMenu> menuList = sysMenu.getChildren();

            if (!menuList.isEmpty() && "M".equals(sysMenu.getMenuType())) {
                routerVO.setAlwaysShow(true);
                routerVO.setRedirect("noRedirect");
                routerVO.setChildren(buildMenus(menuList));

            } else if (isMenuFrame(sysMenu)) {
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO children = new RouterVO();
                children.setPath(sysMenu.getPath());
                children.setComponent(sysMenu.getComponent());
                children.setName(StringUtils.capitalize(sysMenu.getPath()));
                children.setMeta(new MetaVO(sysMenu.getMenuName(), sysMenu.getIcon(), StringUtils.equals("1", sysMenu.getIsCache())));
                childrenList.add(children);
                routerVO.setChildren(childrenList);
            }
            routers.add(routerVO);
        }
        return routers;
    }

    public String getRouteName(SysMenu sysMenu) {
        String routerName = StringUtils.capitalize(sysMenu.getPath());
        if (isMenuFrame(sysMenu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    public String getRouterPath(SysMenu sysMenu) {
//        System.out.println("getRouterPath>>>" + sysMenu.toString());
        String routerPath = sysMenu.getPath();
//        System.out.println("getParentId()==>" + sysMenu.getParentId());
//        System.out.println("getMenuType()==>" + sysMenu.getMenuType());
//        System.out.println("getIsFrame()==>" + sysMenu.getIsFrame());
        if (sysMenu.getParentId() == 0 && "M".equals(sysMenu.getMenuType())
                && "1".equals(sysMenu.getIsFrame())) {
//            System.out.println("11111");
            routerPath = "/" + sysMenu.getPath();
        } else if (isMenuFrame(sysMenu)) {
//            System.out.println("22222");
            routerPath = "/";
        }
//        System.out.println("getRouterPath>>>>>>>>>" + routerPath);
        return routerPath;
    }

    public String getComponent(SysMenu sysMenu) {
        String component = "Layout";
        if (sysMenu.getComponent() != null && !sysMenu.getComponent().equals("") && !isMenuFrame(sysMenu)) {
            component = sysMenu.getComponent();
        } else if (sysMenu.getComponent() == null || sysMenu.getComponent().equals("") && isParentView(sysMenu)) {
            component = "ParentView";
        }

        return component;
    }

    public boolean isMenuFrame(SysMenu sysMenu) {
        return sysMenu.getParentId() == 0 && "C".equals(sysMenu.getMenuType())
                && sysMenu.getIsFrame().equals("1");
    }

    public boolean isParentView(SysMenu sysMenu) {
        return sysMenu.getParentId() != 0 && "M".equals(sysMenu.getMenuType());
    }

    public List<SysMenu> getSysMenuList(SysMenu sysMenu, Long userId) {
        List<SysMenu> sysMenuList;
        if (SysUser.isAdmin(userId)) {
            sysMenuList = sysMenuMapper.getSysMenuList(sysMenu);
        } else {
            sysMenu.getParams().put("userId", userId);
            sysMenuList = sysMenuMapper.getSysMenuListByUserId(sysMenu);
        }
        return sysMenuList;
    }

    public List<SysMenu> getSysMenuList(SysMenu sysMenu) {
        return sysMenuMapper.getSysMenuList(sysMenu);
    }

    public List<SysMenu> getSysMenuList(Long userId) {
        return getSysMenuList(new SysMenu(), userId);
    }

    public int insertMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    public int deleteByMenuId(Long menuId) {
        return sysMenuMapper.deleteByMenuId(menuId);
    }

    public List<TreeSelect> buildSysMenuTreeSelect(List<SysMenu> sysMenuList) {
        List<SysMenu> menuList = buildSysMenuTree(sysMenuList);
        return menuList.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public List<SysMenu> buildSysMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for (SysMenu dept : menus) {
            tempList.add(dept.getMenuId());
        }
        for (SysMenu menu : menus) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    public List<Integer> getSysMenuListByRoleId(Long roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        SysRole role = sysRoleMapper.getSysRole(sysRole);
        return sysMenuMapper.getSysMenuListByRoleId(roleId, role.isMenuCheckStrictly());
    }

    public Set<String> getUserPermissionsByUserId(Long userId) {
        List<String> perms = sysMenuMapper.getSysUserPermissionsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    public SysMenu getSysMenuByMenuId(Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        return sysMenuMapper.getSysMenu(sysMenu);
    }
}
