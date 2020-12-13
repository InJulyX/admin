package com.admin.controller.monitor;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.DatabaseInfoView;
import com.admin.entity.Result;
import com.admin.service.DatabaseInfoViewService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/monitor/database")
public class DatabaseController extends BaseController {
    final
    DatabaseInfoViewService databaseInfoViewService;

    public DatabaseController(DatabaseInfoViewService databaseInfoViewService) {
        this.databaseInfoViewService = databaseInfoViewService;
    }

    @GetMapping(value = "/list")
    @RequiresPermissions("monitor:database:list")
    public Result getList(DatabaseInfoView databaseInfoView) {
        startPage();
        List<?> list = databaseInfoViewService.getList(databaseInfoView);
        return getResultInfo(list);
    }

    @GetMapping(value = {"/vacuum", "/vacuum/{tableName}"})
    @Log(title = "数据库管理", businessType = BusinessType.VACUUM)
    @RequiresPermissions("monitor:database:vacuum")
    public void vacuum(@PathVariable(required = false) String tableName) {
        if (tableName == null) {
            databaseInfoViewService.vacuumAll();
        } else {
            databaseInfoViewService.vacuum(tableName);
        }
    }
}
