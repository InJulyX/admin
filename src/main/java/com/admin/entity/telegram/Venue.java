package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:29
 */

import lombok.Data;

@Data
public class Venue {
    private Location location;
    private String title;
    private String address;
    private String foursquare_id;
    private String foursquare_type;
    private String google_place_id;
    private String google_place_type;
}
