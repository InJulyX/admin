package com.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginLog {
    private Long id;
    private String username;
    private String ip;
    private String location;
    private String os;
    private String browser;
    private String status;
    private String msg;
    private Timestamp loginTime;

    @Override
    public String toString() {
        return username + ": {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
