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

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description:
     * 测试findByNameLike方法
     */
    @Test
    public void findByNameLikeTest() {
        logger.info("实例化一个course");
        Course testCourse = new Course();
        testCourse.setName("test");
        logger.info("保存course");
        courseRepository.save(testCourse);
        logger.info("用目标方法查找出course");
        List<Course> courses = courseRepository.findByNameLike(testCourse.getName());
        logger.info("断言查找到course和原course相同");
        for (Course course : courses) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
        }
    }

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description:
     * 测试findBySemester
     */
    @Test
    public void findBySemesterTest() {
        logger.info("实例化一个学期");
        Semester semester = new Semester();
        semesterRepository.save(semester);
        logger.info("实例化一个课程");
        Course course = new Course();
        logger.info("课程的学期等于实例化的学期");
        course.setSemester(semester);
        courseRepository.save(course);
        logger.info("调用测试方法");
        List<Course> courseList = courseRepository.findBySemester(semester);
        logger.info("断言结果");
        for (Course course1 : courseList) {
            assertThat(course1.getSemester()).isEqualTo(semester);
        }
    }
}
