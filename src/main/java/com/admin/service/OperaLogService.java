package com.admin.service;

import com.admin.entity.OperaLog;
import com.admin.mapper.OperaLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperaLogService {
    @Autowired
    OperaLogMapper operaLogMapper;

    public int insert(OperaLog operaLog) {
        return operaLogMapper.insert(operaLog);
    }
}
