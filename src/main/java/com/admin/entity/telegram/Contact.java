package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:28
 */

import lombok.Data;

@Data
public class Contact {
    private String phone_number;
    private String first_name;
    private String last_name;
    private Integer user_id;
    private String vcard;
}
