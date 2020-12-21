package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:08
 */

import lombok.Data;

@Data
public class Chat {
    private int id;
    private String type;
    private String title;
    private String username;
    private String first_name;
    private String last_name;
    private ChatPhoto photo;
    private String bio;
    private String description;
    private String invite_link;
    private Message pinned_message;
    private ChatPermissions permissions;
    private int slow_mode_delay;
    private String sticker_set_name;
    private boolean can_set_sticker_set;
    private int linked_chat_id;
    private ChatLocation location;
}
