package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @GetMapping
    public Iterable<Student> getAll(){
        Iterable<Student> students = studentService.getAll();
        return students;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }


}
