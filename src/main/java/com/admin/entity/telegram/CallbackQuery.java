package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 3:01
 */

import lombok.Data;

@Data
public class CallbackQuery {
    private String id;
    private User from;
    private Message message;
    private String inline_message_id;
    private String chat_instance;
    private String data;
    private String game_short_name;
}
