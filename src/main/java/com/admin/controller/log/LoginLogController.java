package com.admin.controller.log;

import com.admin.controller.BaseController;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.LoginLog;
import com.admin.service.LoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/log/loginLog")
public class LoginLogController extends BaseController {

    final
    LoginLogService loginLogService;

    public LoginLogController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @RequiresPermissions("log:loginLog:list")
    @GetMapping(value = "/list")
    public TableDataInfo getList(LoginLog loginLog) {
        startPage();
        List<?> list = loginLogService.getList(loginLog);
        return getTableData(list);
    }
}
