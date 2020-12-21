package com.admin.service;

import com.admin.entity.database.DatabaseInfoView;
import com.admin.mapper.DatabaseInfoViewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseInfoViewService {
    final
    DatabaseInfoViewMapper databaseInfoViewMapper;

    public DatabaseInfoViewService(DatabaseInfoViewMapper databaseInfoViewMapper) {
        this.databaseInfoViewMapper = databaseInfoViewMapper;
    }

    public List<DatabaseInfoView> getList(DatabaseInfoView databaseInfoView) {
        return databaseInfoViewMapper.getDatabaseInfoList(databaseInfoView);
    }

    public void vacuum(String tableName) {
        databaseInfoViewMapper.vacuum(tableName);

    }

    public void vacuumAll() {
        databaseInfoViewMapper.vacuumAll();
    }
}
