package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;

/**
 * @author chenjie
 * @date 2018/10/11 20:19
 */
public class CourseRepositoryTest extends ScheduleApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(CourseRepository.class.getName());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Test
    public void findTest() {
        Iterable<Course> courses = courseRepository.findAll();
        assertThat(courses).isNotNull();
    }

    @Test
    public void findByNameLikeAndSemesterIdTest() {
        logger.info("新建semester");
        Semester semester = new Semester();
        semesterRepository.save(semester);
        logger.info("新建一个Course");
        Course testCourse = new Course();
        testCourse.setName("test");
        courseRepository.save(testCourse);
        logger.info("通过测试方法查询");
        logger.info("名字为空的情况");
        List<Course> courseList = courseRepository.findByNameLikeAndSemester("", semester);
        logger.info("断言结果");
        for (Course course : courseList) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
            assertThat(course.getSemester()).isEqualTo(semester);
        }
        logger.info("名字不为空");
        List<Course> courseList1 = courseRepository.findByNameLikeAndSemester(testCourse.getName(), semester);
        logger.info("断言结果");
        for (Course course : courseList) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
            assertThat(course.getSemester()).isEqualTo(semester);
        }
    }
}
