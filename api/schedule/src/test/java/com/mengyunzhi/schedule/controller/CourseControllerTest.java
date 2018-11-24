package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import com.mengyunzhi.schedule.service.CourseService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
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
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonArray.toString()))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void selectCourseByScheduleTest() throws Exception {
        // 创建新的课程并持久化
        Course course = new Course();
        courseService.save(course);

        String putUrl = baseUrl + "/select/" + course.getId();

        // 模拟请求，并断言成功
        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("semesterId", "0")
                        .param("week", "0")
                        .param("weekOrders", "1", "2")
                        .param("node", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void findByCourseNameLikeAndSemesterIdTest() throws Exception {
        logger.info("新建semester");
        Semester semester = new Semester();
        semesterRepository.save(semester);

        logger.info("新建一个Course");
        Course testCourse = new Course();
        testCourse.setName("test");
        courseRepository.save(testCourse);

        logger.info("新建一个分页信息");
        int page = 0;
        int size = 3;

        logger.info("构造url模拟访问并断言");
        String queryUrl = baseUrl + "/query";

        logger.info("学期为空");
        this.mockMvc
                .perform(get(queryUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("name", testCourse.getName())
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))

                //.andDo(print())
                .andExpect(status().isOk());

        logger.info("课程名为空");
        this.mockMvc
                .perform(get(queryUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("id", String.valueOf(semester.getId()))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                //.andDo(print())
                .andExpect(status().isOk());

        logger.info("两个都不为空");
        this.mockMvc
                .perform(get(queryUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("name", testCourse.getName())
                        .param("id", String.valueOf(semester.getId()))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                //.andDo(print())
                .andExpect(status().isOk());

    }
}