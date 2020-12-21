package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:28
 */

import lombok.Data;

@Data
public class VideoNote {
    private String file_id;
    private String file_unique_id;
    private Integer length;
    private Integer duration;
    private PhotoSize thumb;
    private Integer file_size;
}
