package com.admin.entity.database;

import lombok.Data;

@Data
public class BotConfig {

    // ID
    private Long id;
    // bot 名称
    private String botName;
    // bot 类型
    private String botType;
    // bot 密钥
    private String appKey;
    private String appSecret;

    private String agentId;
    private String chatBotId;

}
