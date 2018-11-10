package com.mengyunzhi.schedule.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import com.mengyunzhi.schedule.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

@Transactional
@AutoConfigureMockMvc
public class AuthInterceptorTest extends ScheduleApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptorTest.class.getName());

    @Autowired
    MockMvc mockMvc;    // 模拟进行REST请求
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void preHandle() throws Exception {
        logger.info("获取当前登录用户，返回401");
        String meUrl = "/User/me";
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(meUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(401));

        logger.info("用户登录，用户名密码错误，返回401");
        User user = userService.getOneSavedUser();
        userRepository.save(user);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(user));
        jsonObject.put("password", "abc");
        String jsonString = jsonObject.toJSONString();

        String loginUrl = "/User/login";
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(loginUrl)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(401));

        logger.info("用户登录，用户名密码正确，返回200");

        jsonObject.put("password", "cFHXuqByyHb600Xz9lmlrnoE7mHETm7f");
        jsonString = JSONObject.toJSONString(user);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post(loginUrl)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        Cookie cookie = mvcResult.getResponse().getCookie("SESSION");

        logger.info("获取当前用户，返回200");
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(meUrl)
                .cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
}
