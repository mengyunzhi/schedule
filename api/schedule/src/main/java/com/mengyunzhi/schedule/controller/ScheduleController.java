package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import com.mengyunzhi.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
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

    //以下为test
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("getnowschedule/{semesterId}/{weekOrder}")
    @JsonView(View.Schedule.class)
    public List<Schedule> getBySemesterAndWeekOrder(@PathVariable Long semesterId,@PathVariable int weekOrder) {
        return scheduleService.getBySemesterAndWeekOrder(semesterId, weekOrder);
    }

    @GetMapping("/randomPush")
    public void randomPush() throws ParseException {
        scheduleService.randomPush();
    }
}
