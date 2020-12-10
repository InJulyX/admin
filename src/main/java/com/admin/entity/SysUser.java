package com.admin.entity;

import lombok.Data;

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
