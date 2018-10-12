package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 学期服务实现类
 * htxiang
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    //一周的毫秒数
    final long aWeekStamp = 7 * 24 * 60 * 60 * 1000;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    SemesterRepository semesterRepository;
    public Semester add(Semester semester) {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        //计算学期总周次
        double startTime = Double.parseDouble(semester.getStartTime());
        double endTime  = Double.parseDouble(semester.getEndTime());
        double totalTime = endTime - startTime;
        if (totalTime <= 0) {return null;}
        int totalWeek = (int) (totalTime / aWeekStamp) + 1;

        semesterRepository.save(semester);

        //根据总周次新建行程
        for (int weekOrder = 1; weekOrder <= totalWeek; weekOrder++) {
            for(int week = 1; week <= 7; week++) {
                for (int node = 1; node <= 5; node++) {
                    Schedule schedule = new Schedule();
                    schedule.setSemester(semester);
                    schedule.setWeekOrder(weekOrder);
                    schedule.setWeek(week);
                    schedule.setNode(node);
                    schedules.add(schedule);
                }
            }
        }

        scheduleService.saveAll(schedules);
        semester.setSchedules(schedules);
        return semesterRepository.save(semester);
    }

    @Override
    public Iterable<Semester> getAll() {
        return semesterRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Semester semester = semesterRepository.findOne(id);
        List<Schedule> schedules = semester.getSchedules();
        if (schedules != null) {
            scheduleService.delteAll(schedules);
        }
        semesterRepository.delete(id);
        return;
    }

    @Override
    public void resetStatus() {
        Iterable<Semester> semesters = semesterRepository.findAll();
        for (Semester semester :
                semesters) {
            semester.setStatus(false);
        }
        return;
    }

    @Override
    public void activeSemester(Long id) {
        resetStatus();
        Semester semester = semesterRepository.findOne(id);
        semester.setStatus(true);
        semesterRepository.save(semester);
        return;
    }

    @Override
    public Semester update(Long id, Semester semester) {
        //找到要跟更新的实体并删除
        Semester oldSemester = semesterRepository.findOne(id);
        delete(oldSemester.getId());
        //保存新实体
        return add(semester);
    }

    @Override
    public Semester getById(Long id) {
        return semesterRepository.findOne(id);
    }

    @Override
    public List<Semester> getByName(String name) {
        return semesterRepository.findByName(name);
    }
}
