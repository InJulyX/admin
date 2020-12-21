package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 12:28
 */

import lombok.Data;

@Data
public class ForceReply {
    private Boolean force_reply = true;
    private Boolean selective;
}
