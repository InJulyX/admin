package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 15:33
 */

import lombok.Data;

@Data
public class InputMediaDocument {
    private String type;
    private String media;
    private String thumb;
    private String caption;
    private String parse_mode;
    private MessageEntity[] caption_entities;
    private Boolean disable_content_type_detection;
}
