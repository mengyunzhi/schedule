package com.mengyunzhi.schedule.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyunzhi.schedule.entity.Semester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        Set<String> key = maps.keySet();
        Iterator<String> iter = key.iterator();
        while(iter.hasNext()) {
            String field = iter.next();
            if (field == "errmsg") {
                assertThat(maps.get(field)).isEqualTo("ok");
            }
            if (field == "errcode") {
                assertThat(maps.get(field)).isEqualTo(0);
            }
        }


        assertThat(stringResponseEntity.getStatusCodeValue()).isEqualTo(200);
    }


}