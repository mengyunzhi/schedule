package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SemesterRepository semesterRepository;

    /**
     * 增加行程
     * @param semester  行程的学期
     * @param weekOrder 周次
     * @param node      节次
     * @param week      星期
     * @return          增加的行程
     */
    public Schedule add(Semester semester, int weekOrder, int node, int week) {
            Schedule schedule = new Schedule();
            schedule.setSemester(semester);
            schedule.setWeekOrder(weekOrder);
            schedule.setWeek(week);
            schedule.setNode(node);
            scheduleRepository.save(schedule);
            return schedule;
    }

    /**
     * 批量保存行程
     * @param schedules 要保存的行程
     */
    @Override
    public void saveAll(ArrayList<Schedule> schedules) {
        scheduleRepository.save(schedules);
    }

    /**
     * 批量删除行程
     * @param schedules 要删除的行程
     */
    @Override
    public void delteAll(List<Schedule> schedules) {
        scheduleRepository.delete(schedules);
    }

    /**
     * 根据当前学期和周次并按照节次 周次排序返回行程
     * @param semesterId  当前学期
     * @param weekOrder 当前周次
     * @return
     */
    @Override
    public List<Schedule> getBySemesterAndWeekOrder(Long semesterId, int weekOrder) {
        Semester semester = semesterRepository.findOne(semesterId);
        if (semester == null) {return null;}
        return scheduleRepository.findBySemesterAndWeekOrderOrderByWeekAscNodeAsc(semester, weekOrder);
    }
}
