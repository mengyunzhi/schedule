package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    List<Semester> findByName(String name);
}
