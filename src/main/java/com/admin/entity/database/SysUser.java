package com.admin.entity.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
public class SysUser {
    private Long id;
    private String username;
//    @JsonIgnore
    @JsonProperty
    private String password;
    private Boolean isActive;
//    private Set<String> roles;
    private List<SysRole> roles;
    private Long[] roleIds;
    private String avatar;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String createBy;
    private String updateBy;
    private String remark;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }


    public static boolean isAdmin(Long userId) {
        return userId != null && userId <= 10;
    }

    public static boolean isAdmin(String username){
        return Objects.equals(username, "system");
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
