package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行程控制器
 * htxiang
 */

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("getnowschedule/{semesterId}/{weekOrder}")
    @JsonView(View.Schedule.class)
    public List<Schedule> getBySemesterAndWeekOrder(@PathVariable Long semesterId,@PathVariable int weekOrder) {
        return scheduleService.getBySemesterAndWeekOrder(semesterId, weekOrder);
    }
}
