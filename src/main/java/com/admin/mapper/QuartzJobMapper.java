package com.admin.mapper;

/*
 * Author: InJuly
 * time  : 2020/12/19 0:45
 */

import com.admin.entity.database.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuartzJobMapper {
    List<QuartzJob> getQuartzJobList(QuartzJob quartzJob);
}
