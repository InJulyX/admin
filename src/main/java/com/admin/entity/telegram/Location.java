package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:29
 */

import lombok.Data;

@Data
public class Location {
    private Float longitude;
    private Float latitude;
    private Float horizontal_accuracy;
    private Integer live_period;
    private Integer heading;
    private Integer proximity_alert_radius;
}
