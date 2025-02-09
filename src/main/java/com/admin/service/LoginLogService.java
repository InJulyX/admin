package com.admin.service;

import com.admin.entity.database.LoginLog;
import com.admin.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;

    public int insert(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }

    public List<?> getList(LoginLog loginLog) {
        return loginLogMapper.getLoginLogList(loginLog);
    }
}
