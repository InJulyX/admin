package com.admin.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private Boolean isActive;
    private Set<String> roles;
    private String avatar;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String createBy;
    private String updateBy;

    public SysUser() {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("root");
        this.roles = roles;
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && userId <= 10;
    }

    @Override
    public String toString() {
        return username + ": {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", roles=" + roles +
                '}';
    }
}
