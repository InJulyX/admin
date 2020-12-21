package com.admin.service;

import com.admin.entity.database.OperaLog;
import com.admin.mapper.OperaLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaLogService {
    final
    OperaLogMapper operaLogMapper;

    public OperaLogService(OperaLogMapper operaLogMapper) {
        this.operaLogMapper = operaLogMapper;
    }

    public int insert(OperaLog operaLog) {
        return operaLogMapper.insert(operaLog);
    }

    public List<OperaLog> getOperaLogList(OperaLog operaLog) {
        return operaLogMapper.getOperaLogList(operaLog);
    }
}
