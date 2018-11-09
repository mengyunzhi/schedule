package com.mengyunzhi.schedule.controller;

import com.alibaba.fastjson.JSONObject;
import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.service.UserService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author liyiheng
 * @date 2018/10/10 18:52
 */


@Transactional
@AutoConfigureMockMvc
public class ControllerTest extends ScheduleApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;
    protected Cookie cookie;

    // 单元测试之前登录
    @Before
    public void before() throws Exception {
        String loginUrl = "/User/login";
        User user = userService.getOneSavedUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        String jsonString = jsonObject.toJSONString();

        // 模拟访问
        MvcResult mvcResult = this.mockMvc
                .perform(post(loginUrl)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        this.cookie = mvcResult.getResponse().getCookie("SESSION");

    }

}
