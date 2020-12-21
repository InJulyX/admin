package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:08
 */

import lombok.Data;

@Data
public class ChatPhoto {
    private String small_file_id;
    private String small_file_unique_id;
    private String big_file_id;
    private String big_file_unique_id;
}
