package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import net.sf.json.JSONArray;
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
    ScheduleRepository scheduleRepository;

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

    // 为课程选择时间 方法测试
    @Test
    public void selectCourseByScheduleTest() {
        // 创建多个行程，并持久化
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);

        // 创建选择行程的数据
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);

        // 创建新的课程并持久化
        Course course = new Course();
        courseRepository.save(course);

        // 调用selectCourseBySchedule方法 选择时间
        courseService.selectCourseBySchedule(course.getId(), scheduleList);

        // 断言成功
        Course newCourse = courseRepository.findOne(course.getId());
        assertThat(newCourse.getScheduleList()).isEqualTo(scheduleList);
    }
}