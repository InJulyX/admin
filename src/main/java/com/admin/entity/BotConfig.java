package com.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BotConfig {
    private Long id;
    private String name;
    private String type;
    private String key;
    private String status;
    private String remark;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String createAt;
    private String updateAt;

}
