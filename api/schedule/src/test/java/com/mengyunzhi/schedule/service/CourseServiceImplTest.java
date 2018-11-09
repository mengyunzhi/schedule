package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    // 为课程选择时间 方法测试
    @Test
    public void selectCourseByScheduleTest() {
        // 创建多个行程，并持久化
        Schedule schedule1 = new Schedule();
        schedule1.setWeek(1);
        schedule1.setNode(1);
        schedule1.setWeekOrder(1);
        Schedule schedule2 = new Schedule();
        schedule2.setWeek(1);
        schedule2.setNode(1);
        schedule2.setWeekOrder(2);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);

        // 创建新的课程并持久化
        Course course = new Course();
        courseService.save(course);

        // 创建一个数组链表
        List<Schedule> schedules = new ArrayList<>();

        // 选择的行程
        int week = 1;
        int node = 1;
        List<Integer> weekorders = new ArrayList<>();
        weekorders.add(1);
        weekorders.add(2);

        // 循环遍历weekorders
        for (Integer weekorder :
                weekorders) {
            Schedule schedule = scheduleRepository.findByWeekAndNodeAndWeekOrder(week, node, weekorder);
                schedules.add(schedule);
        }

        // 调用selectCourseBySchedule方法 选择时间
        courseService.selectCourseBySchedule(course.getId(), week, node, weekorders);

        // 断言成功
        Course newCourse = courseRepository.findOne(course.getId());
        for (int i = 0; i < schedules.size(); i ++) {
            assertThat(newCourse.getScheduleList().get(i)).isEqualTo(schedules.get(i));
        }
    }

    @Test
    public void findByCourseNameLikeAndSemesterIdTest() {
        logger.info("新建semester");
        Semester semester = new Semester();
        semesterRepository.save(semester);
        logger.info("新建一个Course");
        Course testCourse = new Course();
        testCourse.setName("test");
        courseRepository.save(testCourse);
        logger.info("通过测试方法查询");
        logger.info("名字为空的情况");
        List<Course> courseList = courseService.findCourseByNameAndSemesterId(semester.getId(), "");
        logger.info("断言结果");
        for (Course course : courseList) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
            assertThat(course.getSemester()).isEqualTo(semester);
        }
        logger.info("名字不为空");
        List<Course> courseList1 = courseService.findCourseByNameAndSemesterId(semester.getId(), testCourse.getName());
        logger.info("断言结果");
        for (Course course : courseList) {
            assertThat(course.getName()).isEqualTo(testCourse.getName());
            assertThat(course.getSemester()).isEqualTo(semester);
        }
    }

}