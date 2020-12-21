package com.admin.quartz;

/*
 * Author: InJuly
 * time  : 2020/12/18 23:59
 */

import org.springframework.stereotype.Component;

@Component(value = "quartzTask")
public class QuartzTask {
    public void testTask(int i) {
        System.out.println(System.currentTimeMillis() / 1000);
    }
}
