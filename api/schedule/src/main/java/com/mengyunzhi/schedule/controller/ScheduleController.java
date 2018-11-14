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

    /**
     * 这个功能仅仅是为了测试首页显示
     * 为了保存学生和课程
     * 在学生管理和课程管理完成后删除
     * 因此把业务逻辑写在这了
     * @param id
     */
    @PutMapping("/init/{id}")
    public void initTest(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.findOne(id);

        Student student1 = new Student();
        student1.setName("student" + id.toString());
        Student student2 = new Student();
        student2.setName("student2" + id.toString());
        Student student3 = new Student();
        student3.setName("student3" + id.toString());
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);


        ArrayList<Student> students1 = new ArrayList<Student>();
        students1.add(student1);
        students1.add(student2);
        ArrayList<Student> students2 = new ArrayList<Student>();
        students2.add(student1);
        students2.add(student3);

        Course course1 = new Course();
        course1.setStudentList(students1);
        Course course2 = new Course();
        course2.setStudentList(students2);
        courseRepository.save(course1);
        courseRepository.save(course2);

        ArrayList<Course> tc1 = new ArrayList<Course>();
        tc1.add(course1);
        tc1.add(course2);
        student1.setCourseList(tc1);
        studentRepository.save(student1);
        ArrayList<Course> tc2 = new ArrayList<Course>();
        tc2.add(course1);
        student2.setCourseList(tc2);
        studentRepository.save(student2);
        ArrayList<Course> tc3 = new ArrayList<Course>();
        tc3.add(course2);
        student3.setCourseList(tc3);
        studentRepository.save(student3);


        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        courses.add(course2);

        schedule.setCourseList(courses);
        scheduleRepository.save(schedule);
        return;
    }

    @GetMapping("/randomPush")
    public void randomPush() throws ParseException {
        scheduleService.randomPush();
    }
}
