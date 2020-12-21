package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:48
 */

import lombok.Data;

@Data
public class PollOption {
    private String text;
    private Integer voter_count;
}
