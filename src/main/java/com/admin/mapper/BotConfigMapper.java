package com.admin.mapper;

import com.admin.entity.database.BotConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BotConfigMapper {
    void insert(BotConfig botConfig);

    void insertBatch(List<BotConfig> botConfigList);

    BotConfig getBotConfig(BotConfig botConfig);

}
