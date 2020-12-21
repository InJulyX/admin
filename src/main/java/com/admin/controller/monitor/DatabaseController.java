package com.admin.controller.monitor;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.DatabaseInfoView;
import com.admin.service.DatabaseInfoViewService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public TableDataInfo getList(DatabaseInfoView databaseInfoView) {
        startPage();
        List<?> list = databaseInfoViewService.getList(databaseInfoView);
        return getTableData(list);
    }

    @GetMapping(value = {"/vacuum", "/vacuum/{tableName}"})
    @Log(title = "数据库管理", businessType = BusinessType.VACUUM)
    @RequiresPermissions("monitor:database:vacuum")
    public AjaxResult vacuum(@PathVariable(required = false) String tableName) {
        if (tableName == null) {
            databaseInfoViewService.vacuumAll();
        } else {
            databaseInfoViewService.vacuum(tableName);
        }
        return AjaxResult.success();
    }
}
