package com.admin.controller.sms;

import com.admin.controller.BaseController;
import com.admin.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sms/sendLog")
public class SendLogController extends BaseController {
    @GetMapping(value = "/list")
    public Result getList(){
        startPage();
        return getResultInfo(1);
    }
}
