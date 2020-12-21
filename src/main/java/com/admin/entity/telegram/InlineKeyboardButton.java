package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:24
 */

import lombok.Data;

@Data
public class InlineKeyboardButton {
    private String text;
    private String url;
    private LoginUrl login_url;
    private String callback_data;
    private String switch_inline_query;
    private String switch_inline_query_current_chat;
    private CallbackGame callback_game;
    private Boolean pay;
}
