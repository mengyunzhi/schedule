package com.mengyunzhi.schedule.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyunzhi.schedule.entity.Semester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class ScheduleServiceImplTest extends ServiceTest{
    @Autowired
    ScheduleService scheduleService;

    @Autowired SemesterService semesterService;

    @Test
    public void sendToDD() throws IOException {
        Semester semester = new Semester();
        semester.setStatus(true);
        semester.setStartTime(Long.toString(new Date().getTime()));
        semester.setEndTime(Long.toString(new Date().getTime() + 343423434));
        semesterService.add(semester);
        ResponseEntity<String> stringResponseEntity = scheduleService.sendToDD();
        String body = stringResponseEntity.getBody();



        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> maps = objectMapper.readValue(
                body, Map.class);
        assertThat(maps.get("errmsg")).isEqualTo("ok");
        assertThat(maps.get("errcode")).isEqualTo(0);
        assertThat(stringResponseEntity.getStatusCodeValue()).isEqualTo(200);
    }


}