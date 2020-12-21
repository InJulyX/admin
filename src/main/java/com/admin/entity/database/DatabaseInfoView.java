package com.admin.entity.database;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DatabaseInfoView {
    private String database;
    private String schema;
    private String tableName;
    private String tableSize;
    private String indexSize;
    private String totalSize;
    private Long tableRow;
    private Long columnRow;
    private Integer indexCount;
    private String kind;
    private String remark;
    private Timestamp lastAnalyze;
}
