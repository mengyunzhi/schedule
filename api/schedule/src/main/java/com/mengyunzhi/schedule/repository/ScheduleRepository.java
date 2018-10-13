package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findBySemesterAndWeekOrder(Semester semester, int weekOrder);

    List<Schedule> findBySemester(Semester semester);
}
