package com.admin.controller;

/*
 * Author: InJuly
 * time  : 2020/12/21 0:43
 */

import com.admin.entity.telegram.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/noAuth/telegram/bot")
public class TelegramCallBackController {
    @PostMapping(value = "/1442712562:AAGwYuxv4hbJEqU-m3zVRLWtbDdq4zcSohI")
    public void getUpdate(@RequestBody Update update) {
        System.out.println("update ==> " + update.toString());
    }
}

