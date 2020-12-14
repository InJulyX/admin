package com.admin.entity;

import java.sql.Timestamp;

public class SmsSendLog {
    private Long id;
    private String username;
    private String msgContent;
    private Long gatewayId;
    private String status;
    private Timestamp addTime;
    private Timestamp reportTime;
    private Timestamp notifyTime;
    private String mobile;
}
