package com.admin.entity;

import com.admin.entity.database.SysMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String label;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {
    }

    public TreeSelect(SysMenu sysMenu) {
        this.id = sysMenu.getMenuId();
        this.label = sysMenu.getMenuName();
        this.children = sysMenu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
}
