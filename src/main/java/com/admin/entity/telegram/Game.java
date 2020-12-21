package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:28
 */

import lombok.Data;

@Data
public class Game {
    private String title;
    private String description;
    private PhotoSize[] photo;
    private String text;
    private MessageEntity[] text_entities;
    private Animation animation;
}
