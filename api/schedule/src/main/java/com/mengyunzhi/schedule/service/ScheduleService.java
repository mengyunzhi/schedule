package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;


import java.util.ArrayList;
import java.util.List;

public interface ScheduleService {
    //只有在新建学期时新建行程
    void saveAll(ArrayList<Schedule> schedules);
    //批量删除行程
    void delteAll(List<Schedule> schedules);
}
