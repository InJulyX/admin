package com.admin.controller;

import com.admin.entity.BotConfig;
import com.admin.mapper.BotConfigMapper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    BotConfigMapper botConfigMapper;

    @RequestMapping(value = "/test1")
    @RequiresRoles("admin")
    public int test1() {
        return 1;
    }

    @RequestMapping(value = "/test2")
    @RequiresRoles("root")
    public int test2() {
        return 2;
    }

}

