package com.admin.entity.database;

/*
 * Author: InJuly
 * time  : 2020/12/19 0:36
 */

import com.admin.constant.ScheduleConstants;
import com.admin.utils.QuartzUtils;
import com.admin.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QuartzJob {
    private Long jobId;
    private String jobName;
    private String jobGroup;
    private String invokeTarget;
    private String cronExpression;
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getNextValidTime() {
        if (StringUtils.isNotEmpty(cronExpression)) {
            return QuartzUtils.getNextExecution(cronExpression);
        }
        return null;
    }
}
