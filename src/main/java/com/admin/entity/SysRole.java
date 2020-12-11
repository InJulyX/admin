package com.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysRole {
    private Long id;
    private String name;
    private String key;
    private String remark;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String createBy;
    private String updateBy;
    private String status;
    private boolean menuCheckStrictly;
    private Long[] menuIds;
}
