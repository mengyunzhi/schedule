package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class StudentRepositoryTest extends ScheduleApplicationTests {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void findByCourseList() {
        List<Course> courses = new ArrayList<Course>();
        Course course = new Course();
        course.setName("test");
        Course course1 = new Course();
        course1.setName("test2");
        courses.add(course);
        courses.add(course1);
        courseRepository.save(courses);

        Student student = new Student();
        student.setCourseList(courses);
        Student student2 = new Student();
        studentRepository.save(student);
        studentRepository.save(student2);

        Set<Student> students = studentRepository.findByCourseListIn(courses);
        for (Student stu:
             students) {
            assertThat(student).isEqualTo(student);
        }
    }
}