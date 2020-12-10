package com.admin.mapper;

import com.admin.entity.OperaLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OperaLogMapper {
    int insert(OperaLog operaLog);
}
