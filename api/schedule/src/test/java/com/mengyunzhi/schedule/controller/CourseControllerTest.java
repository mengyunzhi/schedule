package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.service.CourseService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 课程
 */
public class CourseControllerTest extends ControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CourseControllerTest.class);   // 日志
    private final String baseUrl = "/Course";
    @Autowired
    private CourseService courseService;        // 课程
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CourseRepository courseRepository;


    @Test
    public void deleteAll() throws Exception {
        logger.info("创建多个课程");
        Course course1 = new Course();
        Course course2 = new Course();
        courseService.save(course1);
        courseService.save(course2);

        logger.info("组建删除数据");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(course1);
        jsonArray.add(course2);

        logger.info("拼接url");
        String deleteUrl = baseUrl + "/deleteAll";

        logger.info("模拟请求，并断言请求成功");
        this.mockMvc
                .perform(delete(deleteUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonArray.toString()))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description:
     * 测试findCourseByName
     */
    @Test
    public void findCourseByName() throws Exception {
        logger.info("新建并保存一个Course");
        Course course = new Course();
        courseRepository.save(course);
        logger.info("拼接URL");
        String queryUrl = baseUrl + "/query/name/" + course.getName();
        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(queryUrl))
                .andExpect(status().isOk());
    }

    public void findCourseBySemesterId() throws Exception {
        logger.info("实例化一个学期");
        Semester semester = new Semester();
        semesterRepository.save(semester);
        logger.info("实例化一个课程");
        Course course = new Course();
        logger.info("课程的学期等于实例化的学期");
        course.setSemester(semester);
        courseRepository.save(course);
        logger.info("拼接URL");
        String queryUrl = baseUrl + "/query/semester/" + semester.getId();
        logger.info("模拟访问并断言结果");
        this.mockMvc
                .perform(get(queryUrl))
                .andDo(print())
                .andExpect(status().isOk());
    // 为课程选择时间  方法测试
    @Test
    public void selectCourseByScheduleTest() throws Exception {
        // 创建多个行程并持久化
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);

        // 组建选课时间
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(schedule1);
        jsonArray.add(schedule2);

        // 新建课程并持久化
        Course course = new Course();
        courseRepository.save(course);

        // 为课程选择时间
        course.setScheduleList(jsonArray);

        String putUrl = baseUrl + "/select/" + course.getId().toString();

        // 模拟请求，并断言成功
        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonArray.toString()))
                //.andDo(print())
                .andExpect(status().isOk());
    }
}