package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 15:31
 */

import lombok.Data;

@Data
public class InputMediaAudio {
    private String type;
    private String media;
    private String thumb;
    private String caption;
    private String parse_mode;
    private MessageEntity[] caption_entities;
    private Integer duration;
    private String performer;
    private String title;
}
