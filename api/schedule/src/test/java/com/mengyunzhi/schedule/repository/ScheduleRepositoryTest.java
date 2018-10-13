package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.service.SemesterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class ScheduleRepositoryTest extends ScheduleApplicationTests {

    @Autowired
    SemesterService semesterService;

    @Autowired ScheduleRepository scheduleRepository;

    @Test
    public void findBySemesterAndWeekOrderOrderByNodeAscWeekAsc() {
        Semester semester = new Semester();
        semester.setName("test1");
        semester.setStartTime("1514739661000");
        semester.setEndTime("1514739671000");
        semesterService.add(semester);
        Long id = semester.getId();

        List<Schedule> schedules = scheduleRepository.findBySemesterAndWeekOrderOrderByWeekAscNodeAsc(semester, 1);

        int week = 0;
        int node = 0;
        int weekSign = 1;
        int nodeSign = 0;
        for (Schedule schedule :
                schedules) {
            nodeSign++;
            assertThat(schedule.getWeekOrder()).isEqualTo(1);
            assertThat(schedule.getSemester().getId()).isEqualTo(id);
            //判断节次生序排列
            assertThat(schedule.getNode()).isEqualTo(nodeSign);
            assertThat(schedule.getWeek()).isEqualTo(weekSign);
            node++;
            if (node % 5 == 0) {
                weekSign++;
                nodeSign = 0;
            }
        }
    }
}