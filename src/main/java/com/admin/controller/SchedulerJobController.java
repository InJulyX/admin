package com.admin.controller;

/*
 * Author: InJuly
 * time  : 2020/12/19 0:28
 */

import com.admin.entity.TableDataInfo;
import com.admin.entity.database.QuartzJob;
import com.admin.service.SchedulerJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/scheduler/job")
public class SchedulerJobController extends BaseController {
    final
    SchedulerJobService schedulerJobService;

    public SchedulerJobController(SchedulerJobService schedulerJobService) {
        this.schedulerJobService = schedulerJobService;
    }

    @GetMapping(value = "/list")
    @RequiresPermissions(value = "scheduler:job:list")
    public TableDataInfo getList(QuartzJob quartzJob) {
        startPage();
        List<?> list = schedulerJobService.getList(quartzJob);
        return getTableData(list);
    }
}
