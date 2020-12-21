package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:28
 */

import lombok.Data;

@Data
public class Voice {
    private String file_id;
    private String file_unique_id;
    private Integer duration;
    private String mime_type;
    private Integer file_size;
}
