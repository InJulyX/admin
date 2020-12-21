package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:20
 */

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReplyKeyboardMarkup {
    private ArrayList<KeyboardButton> keyboard;
    private Boolean resize_keyboard;
    private Boolean ont_time_keyboard;
    private Boolean selective;
}
