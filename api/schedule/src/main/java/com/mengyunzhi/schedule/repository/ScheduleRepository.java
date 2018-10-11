package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}
