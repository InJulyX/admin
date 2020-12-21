package com.admin.service;

import com.admin.entity.database.BotConfig;
import com.admin.mapper.BotConfigMapper;
import com.admin.utils.StrHash;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DingTalkService {
    final
    BotConfigMapper botConfigMapper;

    public DingTalkService(BotConfigMapper botConfigMapper) {
        this.botConfigMapper = botConfigMapper;
    }

    public boolean verifySign(String sign, String timestamp, String botId) throws Exception {
        BotConfig botConfig = getBotConfigByChatBotId(botId);
        System.out.println(botConfig.toString());
        String signStr = timestamp + "\n" + botConfig.getAppSecret();
        String result = StrHash.getHmacSHA256Hash(signStr, botConfig.getAppSecret());
        return Objects.equals(sign, result);
    }

    public BotConfig getBotConfig(BotConfig botConfig) {
        return botConfigMapper.getBotConfig(botConfig);
    }

    public BotConfig getBotConfigByChatBotId(String chatBotId) {
        BotConfig botConfig = new BotConfig();
        botConfig.setChatBotId(chatBotId);
        return botConfigMapper.getBotConfig(botConfig);
    }
}
