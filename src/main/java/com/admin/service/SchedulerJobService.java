package com.admin.service;

/*
 * Author: InJuly
 * time  : 2020/12/19 0:44
 */

import com.admin.entity.database.QuartzJob;
import com.admin.mapper.QuartzJobMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerJobService {
    final
    QuartzJobMapper quartzJobMapper;

    public SchedulerJobService(QuartzJobMapper quartzJobMapper) {
        this.quartzJobMapper = quartzJobMapper;
    }

    public List<QuartzJob> getList(QuartzJob quartzJob) {
        return quartzJobMapper.getQuartzJobList(quartzJob);
    }
}
