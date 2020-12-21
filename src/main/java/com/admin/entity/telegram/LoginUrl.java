package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:25
 */

import lombok.Data;

@Data
public class LoginUrl {
    private String url;
    private String forward_text;
    private String bot_username;
    private Boolean request_write_access;
}
