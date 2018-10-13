package com.mengyunzhi.schedule.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest extends ControllerTest{
    private static final String url = "/student/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception{
        this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception{
        this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }
}
