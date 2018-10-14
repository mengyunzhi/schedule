package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.repository.CourseRepository;
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
    public void deleteByIdTest() {
        //新建三个course实体
        Course [] courses = {new Course(), new Course(), new Course()};

        //批量保存
        for (int i = 0;i < 3;i++) {
            courseRepository.save(courses[i]);
        }

        //将id存到数组中
        Long [] idArray = new Long[3];
        for (int i = 0;i < 3;i++) {
            idArray[i] = courses[i].getId();
        }

        //将id数组封装成List接口类
        List<Long> ids = new ArrayList<Long>(Arrays.asList(idArray));

        //批量删除
        courseService.deleteById(ids);

        //断言删除成功
        assertThat(courseRepository.findAllByIdIn(ids)).isEmpty();
    }

    @Test
    public void getByIdTest() {
    }

    @Test
    public void updateByIdAndCourseTest() {
    }
}