package com.admin.entity;

/*
 * Author: InJuly
 * time  : 2020/12/18 0:11
 */

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private List<?> rows;
    private int code;
    private String message;

    public TableDataInfo() {
    }

    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

}
