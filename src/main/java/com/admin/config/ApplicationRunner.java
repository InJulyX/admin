package com.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run(ApplicationArguments args) {
        logger.info("系统启动完成~");
//        String initResult = commonMapper.init();
//        if (initResult == null) {
//            logger.error("数据库连接失败, 请检测数据库连接");
//            preDestroy();
//        }
//        logger.info("数据库连接成功~");
    }

    public static void exitApplication(ConfigurableApplicationContext context) {
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("系统已停止~");
    }
}
