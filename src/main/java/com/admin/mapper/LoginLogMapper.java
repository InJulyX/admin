package com.admin.mapper;

import com.admin.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LoginLogMapper {
    int insert(LoginLog loginLog);

    List<LoginLog> getLoginLogAll();

    List<LoginLog> getLoginLogList(LoginLog loginLog);
}
