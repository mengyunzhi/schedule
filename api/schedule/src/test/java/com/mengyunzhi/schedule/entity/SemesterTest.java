package com.mengyunzhi.schedule.entity;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SemesterTest extends ScheduleApplicationTests {
    @Autowired
    SemesterRepository semesterRepository;
    @Test
    public void setStatus() {

    }
}