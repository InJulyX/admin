package com.admin.mapper;

import com.admin.entity.database.OperaLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OperaLogMapper {
    int insert(OperaLog operaLog);

    int insertBatch(List<OperaLog> operaLogList);

    List<OperaLog> getOperaLogList(OperaLog operaLog);
}
