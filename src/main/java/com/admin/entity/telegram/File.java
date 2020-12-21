package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:57
 */

import lombok.Data;

@Data
public class File {
    private String file_id;
    private String file_unique_id;
    private Integer file_size;
    private String file_path;
}
