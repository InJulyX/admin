package com.admin.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }
}
