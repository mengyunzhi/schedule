package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.service.CourseService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 课程
 */
public class CourseControllerTest extends ControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CourseControllerTest.class);   // 日志
    private final String baseUrl = "/Course";
    @Autowired
    private CourseService courseService;        // 课程

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

}