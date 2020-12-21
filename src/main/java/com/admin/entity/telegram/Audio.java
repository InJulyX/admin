package com.admin.entity.telegram;

import lombok.Data;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:26
 */

@Data
public class Audio {
    private String file_id;
    private String file_unique_id;
    private Integer duration;
    private String performer;
    private String title;
    private String file_name;
    private String mime_type;
    private Integer file_size;
    private PhotoSize thumb;
}
