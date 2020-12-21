package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:27
 */

import lombok.Data;

@Data
public class PhotoSize {
    private String file_id;
    private String file_unique_id;
    private Integer width;
    private Integer height;
    private Integer file_size;
}
