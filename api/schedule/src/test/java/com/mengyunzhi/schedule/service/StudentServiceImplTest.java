package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class StudentServiceImplTest extends ServiceTest {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @Test
    public void save() {
        Student student = new Student();
        studentService.save(student);
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent).isNotNull();
    }
}