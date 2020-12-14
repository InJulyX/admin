package com.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BotConfig {

    // ID
    private Long id;
    // bot 名称
    private String name;
    // bot 类型
    private String type;
    // bot 密钥
    private String key;

    private String status;

    private String createBy;

    private String updateBy;
    // 添加时间
    private Timestamp createTime;
    // 更新时间
    private Timestamp updateTime;
    // 备注
    private String remark;

    @Override
    public String toString() {
        return name + ": {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
