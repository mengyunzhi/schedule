package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    public Schedule add(Semester semester, int weekOrder, int node, int week) {
            Schedule schedule = new Schedule();
            schedule.setSemester(semester);
            schedule.setWeekOrder(weekOrder);
            schedule.setWeek(week);
            schedule.setNode(node);
            scheduleRepository.save(schedule);
            return schedule;
    }

    @Override
    public void saveAll(ArrayList<Schedule> schedules) {
        scheduleRepository.save(schedules);
    }

    @Override
    public void delteAll(List<Schedule> schedules) {
        scheduleRepository.delete(schedules);
    }
}
