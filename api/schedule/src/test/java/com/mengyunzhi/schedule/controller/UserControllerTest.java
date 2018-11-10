package com.mengyunzhi.schedule.controller;

import com.alibaba.fastjson.JSONObject;
import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import com.mengyunzhi.schedule.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@AutoConfigureMockMvc
public class UserControllerTest extends ScheduleApplicationTests {

    private final static Logger logger = LoggerFactory.getLogger(UserControllerTest.class.getName());

    @Autowired
    MockMvc mockMvc;   // 模拟rest请求
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HttpSession httpSession;

    @Test
    public void loginTest() throws Exception {
        // 访问的url
        String url = "/User/login";
        // 初始化需要的json对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "InAbxUsHyQ2dXKbWfueZR6WBfH7ZpLLx");
        jsonObject.put("password", "pWoLgdwzxVHpkDUuYJf7HQqZcx9dyDjq");

        // 将json对象传化为字符串
        String jsonString = jsonObject.toJSONString();

        logger.debug("无此用户名的情况");
        //访问测试方法
        this.mockMvc
                .perform(post(url)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is(401));

        logger.debug("密码不正确的情况");
        User user = userService.getOneSavedUser();
        // 访问方法
        this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonString))
                .andDo(print())
                .andExpect(status().is(401));

        logger.debug("密码正确的情况");
        user.setPassword("pWoLgdwzxVHpkDUuYJf7HQqZcx9dyDjq");
        userRepository.save(user);

        MvcResult mvcResult = this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Cookie cookie = mvcResult.getResponse().getCookie("SESSION");

        logger.debug("获取当前登录用户，断言其Id为登录用户id");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/User/me");
        arrayList.add("/User/getCurrentLoginUser");
        for (String meUrl : arrayList) {
            mvcResult = this.mockMvc
                    .perform(get(meUrl)
                            .cookie(cookie)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            String content = mvcResult.getResponse().getContentAsString();
            jsonObject = JSONObject.parseObject(content);
            Integer getId = (Integer) jsonObject.get("id");
            Assertions.assertThat(getId.longValue()).isEqualTo(user.getId());

            this.mockMvc
                    .perform(get(meUrl)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
        }
    }

    @Test
    public void logoutTest() throws Exception {
        logger.info("用户注销，断言401");
        String logoutUrl = "/User/logout";
        this.mockMvc
                .perform(post(logoutUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));

        logger.info("用户登录");
        User user = userService.getOneSavedUser();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        String jsonString = jsonObject.toJSONString();

        String loginUrl = "/User/login";
        MvcResult mvcResult = this.mockMvc
                .perform(post(loginUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonString))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        logger.info("用户注销(不带COOKIE），401");
        this.mockMvc
                .perform(post(logoutUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));

        logger.info("获取cookie");
        Cookie cookie = mvcResult.getResponse().getCookie("SESSION");

        logger.info("用户注销，200");
        this.mockMvc
                .perform(post(logoutUrl)
                        .cookie(cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        logger.info("用户注销，401");
        this.mockMvc
                .perform(post(logoutUrl)
                        .cookie(cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
}