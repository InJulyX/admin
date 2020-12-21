package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:20
 */

import lombok.Data;

@Data
public class KeyboardButton {
    private String text;
    private Boolean request_contact;
    private Boolean request_location;
    private KeyboardButtonPollType request_poll;
}
