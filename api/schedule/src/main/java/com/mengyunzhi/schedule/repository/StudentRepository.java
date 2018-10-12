package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
