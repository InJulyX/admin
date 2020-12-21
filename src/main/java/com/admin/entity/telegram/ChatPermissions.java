package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:06
 */

import lombok.Data;

@Data
public class ChatPermissions {
    private Boolean can_send_messages;
    private Boolean can_send_media_messages;
    private Boolean can_send_polls;
    private Boolean can_send_other_messages;
    private Boolean can_add_web_page_previews;
    private Boolean can_change_info;
    private Boolean can_invite_users;
    private Boolean can_pin_messages;

}
