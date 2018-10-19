package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

/**
 * @author liyiheng
 * @date 2018/10/10 18:52
 */


@Transactional
@AutoConfigureMockMvc
public class ControllerTest extends ScheduleApplicationTests {
    @Autowired
    MockMvc mockMvc;
}
