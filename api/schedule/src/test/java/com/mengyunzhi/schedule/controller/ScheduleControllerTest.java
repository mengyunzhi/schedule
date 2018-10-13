package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.service.SemesterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScheduleControllerTest extends ControllerTest{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SemesterService semesterService;

    String baseUrl = "/schedule/";

    @Test
    public void getBySemesterAndWeekOrder() throws Exception {
        Semester semester = new Semester();
        semester.setStartTime("1514739661000");
        semester.setEndTime("1514739671000");
        semesterService.add(semester);

        String getUrl = this.baseUrl + "getnowschedule/" + semester.getId().toString() + "/1";
        this.mockMvc.perform(get(getUrl))
                .andDo(print()).andExpect(status().isOk());
    }
}