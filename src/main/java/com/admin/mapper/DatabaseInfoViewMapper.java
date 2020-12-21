package com.admin.mapper;

import com.admin.entity.database.DatabaseInfoView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DatabaseInfoViewMapper {
    List<DatabaseInfoView> getDatabaseInfoList(DatabaseInfoView databaseInfo);
    void vacuum(String tableName);
    void vacuumAll();
}
