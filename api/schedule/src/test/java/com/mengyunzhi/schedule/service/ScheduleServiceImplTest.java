package com.mengyunzhi.schedule.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class ScheduleServiceImplTest extends ServiceTest {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void sendToDD() throws IOException {
        Calendar calendar = Calendar.getInstance();
        Semester semester = new Semester();
        semester.setStatus(true);
        semester.setStartTime(Long.toString(new Date().getTime()));
        semester.setEndTime(Long.toString(new Date().getTime() + 343423434));
        semesterService.add(semester);
        // 新建一个与课程绑定的学生
        Course course = new Course();
        Student student = new Student();
        student.setState(true);
        student.setName("黄挺像");
        course.setStudentList(Collections.singletonList(student));
        student.setCourseList(Collections.singletonList(course));
        courseRepository.save(course);
        studentRepository.save(student);
        Schedule schedule = scheduleService.getBySemesterAndWeekOrder(semester.getId(), calendar.get(Calendar.DAY_OF_WEEK)).get(0);
        schedule.setCourseList(Collections.singletonList(course));


        ResponseEntity<String> stringResponseEntity = scheduleService.sendToDD();
        String body = stringResponseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> maps = objectMapper.readValue(
                body, Map.class);
        assertThat(maps.get("errmsg")).isEqualTo("ok");
        assertThat(maps.get("errcode")).isEqualTo(0);
        assertThat(stringResponseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    /*
     * 随机推送测试
     * @author chenjie
     */
    @Test
    public void randomPush() throws IOException {
        // 添加学生
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        student1.setGroups("groupPo");
        student1.setState(true);
        student1.setName("黄庭祥");

        student2.setGroups("groupZhang");
        student2.setState(true);
        student2.setName("吴闯");

        student3.setGroups("groupPo");
        student3.setState(true);
        student3.setName("潘佳琦");

        student4.setGroups("groupZhang");
        student4.setState(true);
        student4.setName("董浩天");

        // 将学生持久化
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);

        // 向钉钉发送消息

        ResponseEntity<String> stringResponseEntity = scheduleService.randomPush();

        // 断言返回状态
        String body = stringResponseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> maps = objectMapper.readValue(
                body, Map.class);
        assertThat(maps.get("errmsg")).isEqualTo("ok");
        assertThat(maps.get("errcode")).isEqualTo(0);
        assertThat(stringResponseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}