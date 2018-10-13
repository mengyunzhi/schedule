package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
public class ScheduleServiceImplTest extends ServiceTest{
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    SemesterRepository semesterRepository;
}