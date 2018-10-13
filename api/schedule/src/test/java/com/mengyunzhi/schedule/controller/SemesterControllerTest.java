package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import com.mengyunzhi.schedule.service.SemesterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SemesterControllerTest extends ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SemesterRepository semesterRepository;
    @Autowired SemesterService semesterService;
    String baseUrl = "/semester/";
    @Test
    public void add() throws Exception {
        String postUrl = this.baseUrl;
        this.mockMvc
                .perform(post(postUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"startTime\":\"2\",\"endTime\":\"34\"}"))
                .andExpect(status().is(201));
    }

    @Test
    public void delete() throws Exception {
        Semester semester = new Semester();
        semester.setStartTime("1514739661000");
        semester.setEndTime("1515862861000");
        semesterRepository.save(semester);
        Long id = semester.getId();
        String deleteUrl = this.baseUrl + id.toString();
        this.mockMvc.perform(MockMvcRequestBuilders.delete(deleteUrl))
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        String getUrl = this.baseUrl;
        this.mockMvc.perform(get(getUrl))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void activeSemester() throws Exception {

        Semester semester = new Semester();
        semesterRepository.save(semester);

        String putUrl = this.baseUrl + "active/" + semester.getId().toString();
        this.mockMvc.perform(put(putUrl))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Semester newSemester = new Semester();
        newSemester.setStartTime("1514739661000");
        newSemester.setEndTime("1515862861000");
        semesterService.add(newSemester);

        String updateUrl = this.baseUrl + newSemester.getId().toString();
        this.mockMvc.perform(
                put(updateUrl).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"startTime\":\"2\",\"endTime\":\"34\"}")
        ).andExpect(status().isOk());
    }

    @Test
    public void getByName() throws Exception {
        String name = "name";
        Semester semester = new Semester();
        semester.setName(name);
        semesterRepository.save(semester);
        String nameUrl = this.baseUrl + "name/" + semester.getName();

        this.mockMvc.perform(get(nameUrl))
                .andExpect(status().isOk())
                .andDo(print());
    }
}