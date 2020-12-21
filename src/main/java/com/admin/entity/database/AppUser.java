package com.admin.entity.database;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppUser {
    private Long id;
    private String username;
    private String appName;
    private String appType;
    private String appKey;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
}
