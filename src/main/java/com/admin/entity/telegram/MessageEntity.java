package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:21
 */

import lombok.Data;

@Data
public class MessageEntity {
    private String type;
    private Integer offset;
    private Integer length;
    private String url;
    private User user;
    private String language;
}
