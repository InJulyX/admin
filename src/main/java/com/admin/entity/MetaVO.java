package com.admin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MetaVO {
    private String title;
    private String icon;
    private boolean noCache;

    public MetaVO() {
    }

    public MetaVO(String title, String icon) {
        this.icon = icon;
        this.title = title;
    }

    public MetaVO(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }
}
