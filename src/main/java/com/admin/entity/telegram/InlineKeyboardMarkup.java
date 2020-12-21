package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:30
 */

import lombok.Data;

import java.util.ArrayList;

@Data
public class InlineKeyboardMarkup {
    private ArrayList<InlineKeyboardButton> inline_keyboard;
}
