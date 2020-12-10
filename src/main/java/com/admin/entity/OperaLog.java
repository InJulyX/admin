package com.admin.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class OperaLog {
    private Long operaId;
    private String title;
    private Integer businessType;
    private String requestMethod;
    private Integer operatorType;
    private String operaName;
    private String openUrl;
    private String operaIp;
    private String operaLocation;
    private String operaParam;
    private String jsonResult;
    private Integer status;
    private String errorMsg;
    private Timestamp operaTime;
    private String method;
}
