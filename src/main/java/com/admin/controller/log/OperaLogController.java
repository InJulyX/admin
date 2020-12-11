package com.admin.controller.log;

import com.admin.controller.BaseController;
import com.admin.entity.OperaLog;
import com.admin.entity.Result;
import com.admin.service.OperaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/log/operaLog")
public class OperaLogController extends BaseController {

    final
    OperaLogService operaLogService;

    public OperaLogController(OperaLogService operaLogService) {
        this.operaLogService = operaLogService;
    }

    @GetMapping(value = "/list")
    public Result getList(OperaLog operaLog) {
        startPage();
        List<?> list = operaLogService.getOperaLogList(operaLog);
        return getResultInfo(list);
    }
}