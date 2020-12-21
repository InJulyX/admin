package com.admin.utils;

/*
 * Author: InJuly
 * time  : 2020/12/19 0:41
 */

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

public class QuartzUtils {
    public static Date getNextExecution(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
