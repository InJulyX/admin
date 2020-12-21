package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:49
 */

import lombok.Data;

@Data
public class PollAnswer {
    private String poll_id;
    private User user;
    private Integer[] option_ids;
}
