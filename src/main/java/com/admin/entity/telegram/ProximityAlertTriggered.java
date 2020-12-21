package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:30
 */

import lombok.Data;

@Data
public class ProximityAlertTriggered {
    private User traveler;
    private User watcher;
    private Integer distance;
}
