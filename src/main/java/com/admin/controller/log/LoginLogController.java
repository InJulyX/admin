package com.admin.controller.log;

import com.admin.controller.BaseController;
import com.admin.entity.LoginLog;
import com.admin.entity.Result;
import com.admin.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/list")
    public Result getList(LoginLog loginLog) {
        Result result=new Result();
//        startPage();
        List<?> list = loginLogService.getList(loginLog);
        result.setRows(list);
        return result;
    }
}
