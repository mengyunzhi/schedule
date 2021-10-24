package com.mengyunzhi.schedule.config;

import com.mengyunzhi.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class ScheduledJobOneDay{
    @Autowired
    private ScheduleService scheduleService;

    private final static Logger logger = LoggerFactory.getLogger(ScheduledJobOneDay.class.getName());

//    @Scheduled(cron="0 0 8 * * ?")  //定时任务注解
    public void execute()  {
        logger.info("发送信息");
        scheduleService.sendToDD();
    }

    /**
     * 每晚22点触发
     */
    @Scheduled(cron="0 0 22 * * ?")  //定时任务注解
    public void tomorrow()  {
        logger.info("发送信息");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        scheduleService.sendToDD(new Date(calendar.getTimeInMillis()));
    }
}

