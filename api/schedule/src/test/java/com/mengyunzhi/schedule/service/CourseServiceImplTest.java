package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CourseServiceImplTest extends ServiceTest {
    private final static Logger logger = Logger.getLogger(CourseServiceImplTest.class.getName());
    @Autowired
    CourseService courseService;  // 课程服务
    @Autowired
    CourseRepository courseRepository; // 课程
    @Autowired
    SemesterRepository semesterRepository;

    @Test
    public void saveTest() throws Exception {
        logger.info("new一个对象");
        Course course = new Course();

        logger.info("调用保存方法");
        courseRepository.save(course);

        logger.info("去数据表中查这个对象");
        Course newCourse = courseRepository.findOne(course.getId());

        logger.info("断言查询到的值不是null");
        assertThat(newCourse).isNotNull();
    }

    @Test
    public void getAllTest() throws Exception {
        logger.info("new一个对象");
        Course course = new Course();

        logger.info("调用保存方法");
        courseService.save(course);

        List<Course> courseList = (List<Course>) courseService.getAll();
        assertThat(courseList.size()).isNotZero();
    }

    @Test
    public void deleteAllTest() throws Exception {
        logger.info("添加创建多个课程");
        Course course1 = new Course();
        Course course2 = new Course();
        courseService.save(course1);
        courseService.save(course2);

        logger.info("获取所有的课程，断言课程不为空");
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        assertThat(courseList.size()).isEqualTo(2);

        logger.info("批量删除");
        courseService.deleteAll(courseList);

        logger.info("获取所有课程，断言课程为空");
        courseList = (List<Course>) courseRepository.findAll();
        assertThat(courseList.size()).isZero();
    }

    @Test
    public void getByIdTest() {
    }

    @Test
    public void updateByIdAndCourseTest() {
    }

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description: 测试findCourseByName
     */
    @Test
    public void findCourseByName() {
        logger.info("新建并保存一个对象");
        Course testCourse = new Course();
        courseRepository.save(testCourse);
        logger.info("用测试方法查找出对象");
        List<Course> courseList = courseService.findCourseByName(testCourse.getName());
        logger.info("断言两个对象的名字是一样的");
        for (Course course : courseList) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
        }
    }

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description: 测试findCourseBySemester
     */
    @Test
    public void findCourseBySemesterId() {
        logger.info("实例化一个学期");
        Semester semester = new Semester();
        semesterRepository.save(semester);
        logger.info("实例化一个课程");
        Course course = new Course();
        logger.info("课程的学期等于实例化的学期");
        course.setSemester(semester);
        courseRepository.save(course);
        logger.info("调用测试方法");
        List<Course> courseList = courseService.findCourseBySemesterId(semester.getId());
        logger.info("断言结果");
        for (Course course1 : courseList) {
            assertThat(course1.getSemester()).isEqualTo(semester);
        }
    }
}