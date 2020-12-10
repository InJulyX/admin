package com.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    private long total;
    private List<?> rows;
    public Result(){}

    public Result(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
