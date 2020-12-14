package com.admin.controller.sms;

import com.admin.controller.BaseController;
import com.admin.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "smsUserController")
@RequestMapping(value = "/sms/user")
public class UserController extends BaseController {
    @GetMapping(value = "/list")
    public Result getList() {
        startPage();
        return getResultInfo(1);
    }
}
