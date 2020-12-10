package com.admin.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysMenu {
    private Long menuId;
    private String menuName;
    private String parentName;
    private Long parentId;
    private String orderNum;
    private String path;
    private String component;
    private String isFrame;
    private String isCache;
    private String menuType;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    private List<SysMenu> children = new ArrayList<>();

    @Override
    public String toString() {
        return menuName + ": {" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentId=" + parentId +
                ", orderNum='" + orderNum + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", isFrame='" + isFrame + '\'' +
                ", isCache='" + isCache + '\'' +
                ", menuType='" + menuType + '\'' +
                ", visible='" + visible + '\'' +
                ", status='" + status + '\'' +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}
