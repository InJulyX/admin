package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:44
 */

import lombok.Data;

@Data
public class ResponseParameters {
    private Integer migrate_to_chat_id;
    private Integer retry_after;
}
