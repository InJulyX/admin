package com.admin.service;

import com.admin.entity.MetaVO;
import com.admin.entity.RouterVO;
import com.admin.entity.SysMenu;
import com.admin.entity.SysUser;
import com.admin.mapper.SysMenuMapper;
import com.admin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuService {
    final
    SysMenuMapper sysMenuMapper;

    public SysMenuService(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menuList = new ArrayList<>();
//        menuList = sysMenuMapper.selectMenuTreeByUserId(userId);
        menuList = sysMenuMapper.selectMenuTreeAll();
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
        return sysMenuMapper.getSysMenuList(sysMenu);
    }

    public List<SysMenu> getSysMenuList(SysMenu sysMenu) {
        return sysMenuMapper.getSysMenuList(sysMenu);
    }

    public int insertMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    public int deleteByMenuId(Long menuId) {
        return sysMenuMapper.deleteByMenuId(menuId);
    }
}
