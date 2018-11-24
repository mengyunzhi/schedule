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
        student.setState(true);
        student.setCourseList(courses);
        student.setState(true);
        Student student2 = new Student();
        student2.setState(true);
        ArrayList<Course> courses1 = new ArrayList<>();
        student2.setState(true);
        courses1.add(course);
        student2.setCourseList(courses1);
        studentRepository.save(student);
        studentRepository.save(student2);
        studentRepository.save(studentRepository.save(new Student()));

        Set<Student> students = studentRepository.findByStateAndCourseListIn( true,courses);

        assertThat(students.contains(student)).isTrue();
        assertThat(students.contains(student2)).isTrue();
    }
}