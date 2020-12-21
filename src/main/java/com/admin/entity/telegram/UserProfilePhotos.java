package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:55
 */

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserProfilePhotos {
    private Integer total_count;
    private ArrayList<ArrayList<PhotoSize>> photos;
}
