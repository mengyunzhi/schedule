package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SemesterServiceImplTest extends ServiceTest{
    @Autowired SemesterService semesterService;
    @Autowired
    SemesterRepository semesterRepository;
    @Autowired ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Test
    public void add() {
        Semester semester = new Semester();
        semester.setName("semester333");

        semester.setStartTime("1514739661000");
        semester.setEndTime("1514739671000");
        semesterService.add(semester);
        assertThat(semester.getName()).isEqualTo("semester333");
    }

    @Test
    @Transactional
    public void delete() {
        Semester semester = new Semester();
        semester.setName("test1");
        semester.setStartTime("1514739661000");
        semester.setEndTime("1515862861000");
        semesterService.add(semester);

        Long id = semester.getId();
        semesterService.delete(id);
        assertThat(semesterRepository.findOne(id)).isNull();
    }

    @Test
    @Transactional
    public void getAll() {
        Iterable<Semester> semesters = semesterService.getAll();
    }

    @Test
    public void resetStatus() {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        for (int i = 0; i <= 5 ;i++) {
            Semester semester = new Semester();
            semester.setStatus(true);
            semesters.add(semester);
        }
        semesterRepository.save(semesters);
        semesterService.resetStatus();
        Iterable<Semester> reset = semesterRepository.findAll();
        for (Semester semester :
                reset) {
            assertThat(semester.isStatus()).isFalse();
        }
    }

    @Test
    public void activeSemester() {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        for (int i = 0; i <= 5 ;i++) {
            Semester semester = new Semester();
            semester.setStatus(true);
            semesters.add(semester);
        }
        semesterRepository.save(semesters);

        Semester setSemester = new Semester();
        semesterRepository.save(setSemester);
        semesterService.activeSemester(setSemester.getId());

        Iterable<Semester> reset = semesterRepository.findAll();
        for (Semester semester :
                reset) {
            if (semester.getId() == setSemester.getId()) {
                assertThat(semester.isStatus()).isTrue();
            } else {
                assertThat(semester.isStatus()).isFalse();
            }
        }
    }

    @Test
    public void creatScheduleOfSemester() {
        Semester semester = new Semester();
        semesterRepository.save(semester);

        semesterService.creatScheduleOfSemester(semester, 1, 3);
        Iterable<Schedule> schedules = scheduleRepository.findAll();
        int number  = 0;
        int i  = 1;
        for (Schedule schedule :
                schedules) {
            if (schedule.getSemester().getId() == semester.getId()) {
                assertThat(schedule.getWeekOrder()).isEqualTo(i);
                number ++;
                if (number % 35 == 0) {
                    i++;
                }
            }
        }
        assertThat(number).isNotEqualTo(0);
    }

    @Test
    public void deleteScheduleOfSemesterAndWeekorder() {
        Semester semester = new Semester();
        semesterRepository.save(semester);

        semesterService.creatScheduleOfSemester(semester, 1, 2);
        semesterService.deleteScheduleOfSemesterAndWeekorder(semester, 2);
        assertThat(scheduleRepository.findBySemesterAndWeekOrder(semester, 2)).isEmpty();
        semester = semesterRepository.findOne(semester.getId());
        List<Schedule> schedules = semester.getSchedules();
        Iterable<Schedule> schedules2 = scheduleRepository.findAll();
        for (Schedule schedule :
                semester.getSchedules()) {
            assertThat(schedule.getWeekOrder()).isNotEqualTo(2);
        }
    }

    @Test
    public void currentSemester() {
        Semester testSemester = new Semester();
        Date date = new Date();
        Long nowTime = date.getTime();
        Long endTime = nowTime + Long.parseLong("339398828749");
        testSemester.setStatus(true);
        testSemester.setStartTime(Long.toString(nowTime));
        testSemester.setEndTime(Long.toString(endTime));

        Semester pastSemester = new Semester();
        pastSemester.setStatus(true);
        pastSemester.setStartTime("1539398828749");
        pastSemester.setEndTime("1539398828759");



        semesterRepository.save(testSemester);
        semesterRepository.save(pastSemester);
        Semester semester = semesterService.currentSemester();
        assertThat(semester).isEqualTo(testSemester);
    }

    @Test
    public void getSemesterTest() {
        // 新增一个学期
        Semester newSemester = new Semester();

        // 将新增的学期置为激活状态
        newSemester.setStatus(true);

        // 持久化新增的学期
        semesterRepository.save(newSemester);

        // 调用service中的方法获取到激活的学期
        Semester currentSemester = semesterService.getSemester();

        // 断言获取到的学期就是新增的学期
        assertThat(currentSemester).isEqualTo(newSemester);
    }
}