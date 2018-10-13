package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Student;

public interface StudentService {
    Iterable<Student> getAll();

    Student save(Student student);

    Student getById(Long id);

    Student update(Long id, Student student);
}
