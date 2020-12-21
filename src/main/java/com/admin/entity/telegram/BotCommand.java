package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 1:30
 */

import lombok.Data;

@Data
public class BotCommand {
    private String command;
    private String description;
}
