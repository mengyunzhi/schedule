package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学期服务实现类
 * htxiang
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    //一周的毫秒数
    final long aWeekStamp = 7 * 24 * 60 * 60 * 1000;

    //节次
    static final int node = 5;

    //周次
    static final int week = 7;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    /**
     * 增加学期 同时生成绑定学期的行程
     * @param semester 要添加的学期
     * @return 保存后的学期
     */
    public Semester add(Semester semester) {
        //计算学期总周次
        double startTime = Double.parseDouble(semester.getStartTime());
        double endTime  = Double.parseDouble(semester.getEndTime());
        double totalTime = endTime - startTime;
        if (totalTime <= 0) {return null;}
        int totalWeek = (int) (totalTime / aWeekStamp) + 1;
        semesterRepository.save(semester);
        //根据总周次新建行程
        creatScheduleOfSemester(semester, 1, totalWeek);
        return semester;
    }

    /**
     * 根据开始周次和结束周次生成绑定学期相对应的行程
     * @param semester 学期
     * @param startWeek 开始周次
     * @param endWeek   结束周次
     */
    @Override
    public void creatScheduleOfSemester(Semester semester, int startWeek, int endWeek) {
        if (startWeek <= 0 && endWeek <= 0) {return;}
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (; startWeek <= endWeek; startWeek++) {
            for(int week = 1; week <= SemesterServiceImpl.week; week++) {
                for (int node = 1; node <= SemesterServiceImpl.node; node++) {
                    Schedule schedule = new Schedule();
                    schedule.setSemester(semester);
                    schedule.setWeekOrder(startWeek);
                    schedule.setWeek(week);
                    schedule.setNode(node);
                    schedules.add(schedule);
                }
            }
        }
        scheduleService.saveAll(schedules);
        semester.setSchedules(schedules);
        semesterRepository.save(semester);
    }

    /**
     * 根据学期和周次删除行程
     * @param semester 学期
     * @param weekOrder 周次
     */
    @Override
    public void deleteScheduleOfSemesterAndWeekorder(Semester semester, int weekOrder) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules = scheduleRepository.findBySemesterAndWeekOrder(semester, weekOrder);
        scheduleRepository.delete(schedules);
        semester.setSchedules(scheduleRepository.findBySemester(semester));
        semesterRepository.save(semester);
    }

    /**
     * 获得学期的总周次
     * @param semester 学期
     * @return 总周次
     */
    @Override
    public int getSemesterWeekorder(Semester semester) {
        double startTime = Double.parseDouble(semester.getStartTime());
        double endTime  = Double.parseDouble(semester.getEndTime());
        double totalTime = endTime - startTime;
        if (totalTime <= 0) {return 0;}
        int totalWeek = (int) (totalTime / aWeekStamp) + 1;
        return totalWeek;
    }

    /**
     * 返回当前时间的激活学期
     * @return  semester
     */
    @Override
    public Semester currentSemester() {
        List<Semester> semesters = semesterRepository.findByStatus(true);
        long currentTime = new Date().getTime();
        for (Semester semester :
                semesters) {
            long startTime = Long.parseLong(semester.getStartTime());
            long endTime = Long.parseLong(semester.getEndTime());
            if (startTime <= currentTime && endTime >= currentTime) {
                return semester;
            }
        }
        return null;
    }

    /**
     * 获得所有的学期
     * @return
     */
    @Override
    public Iterable<Semester> getAll() {
        return semesterRepository.findAll();
    }

    /**
     * 根据id删除学期 并删除学期绑定的行程
     * @param id 学期的id
     */
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

    /**
     * 重置学期的状态
     */
    @Override
    public void resetStatus() {
        Iterable<Semester> semesters = semesterRepository.findAll();
        for (Semester semester :
                semesters) {
            semester.setStatus(false);
        }
        return;
    }

    /**
     * 激活一个学期 (只能有一个学期处于激活状态)
     * @param id
     */
    @Override
    public void activeSemester(Long id) {
        resetStatus();
        Semester semester = semesterRepository.findOne(id);
        semester.setStatus(true);
        semesterRepository.save(semester);
        return;
    }

    /**
     * 更新学期信息 并更新学期行程
     * @param id 学期的id
     * @param semester  更新的信息
     * @return  更新后的学期
     */
    @Override
    public Semester update(Long id, Semester semester) {
        Semester oldSemester = semesterRepository.findOne(id);
        int oldTotalTime = getSemesterWeekorder(oldSemester);
        int newTotalTime = getSemesterWeekorder(semester);
        if (oldTotalTime > newTotalTime) {
            for (int i = oldTotalTime; i > newTotalTime; i--) {
                deleteScheduleOfSemesterAndWeekorder(oldSemester, i);
            }
        }
        if (oldTotalTime < newTotalTime) {
            creatScheduleOfSemester(oldSemester, oldTotalTime + 1, newTotalTime);
        }
        oldSemester.setName(semester.getName());
        oldSemester.setStartTime(semester.getStartTime());
        oldSemester.setEndTime(semester.getEndTime());
        return semesterRepository.save(oldSemester);
    }

    /**
     * 根据id获取学期
     * @param id
     * @return
     */
    @Override
    public Semester getById(Long id) {
        return semesterRepository.findOne(id);
    }

    /**
     * 通过学期名字返回学期
     * @param name  学期的名字
     * @return
     */
    @Override
    public List<Semester> getByName(String name) {
        return semesterRepository.findByName(name);
    }
}
