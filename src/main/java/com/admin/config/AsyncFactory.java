package com.admin.config;

import com.admin.constant.Constants;
import com.admin.entity.database.LoginLog;
import com.admin.entity.database.OperaLog;
import com.admin.service.LoginLogService;
import com.admin.service.OperaLogService;
import com.admin.utils.IpUtils;
import com.admin.utils.ServletUtils;
import com.admin.utils.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.TimerTask;

public class AsyncFactory {
    public static TimerTask insertLoginLog(final String username, final String status, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = "地址待查询";
                String browser = userAgent.getBrowser().getName();
                String os = userAgent.getOperatingSystem().getName();
                LoginLog loginLog = new LoginLog();
                loginLog.setIp(ip);
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(message);
                loginLog.setUsername(username);
                loginLog.setLocation(address);

                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginLog.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginLog.setStatus(Constants.FAIL);
                }
                SpringUtils.getBean(LoginLogService.class).insert(loginLog);
            }
        };
    }

    public static TimerTask insertOperaLog(final OperaLog operaLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operaLog.setOperaLocation("位置待查询");
                SpringUtils.getBean(OperaLogService.class).insert(operaLog);
            }
        };
    }
}
