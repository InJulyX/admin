package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:23
 */

import lombok.Data;

@Data
public class ReplyKeyboardRemove {
    private Boolean remove_keyboard = true;
    private Boolean selective;
}
