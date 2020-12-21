package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 15:30
 */

import lombok.Data;

@Data
public class InputMediaPhoto {
    private String type;
    private String media;
    private String caption;
    private String parse_mode;
    private MessageEntity[] caption_entities;
}
