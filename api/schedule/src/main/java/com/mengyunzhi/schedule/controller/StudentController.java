package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<Student> getAll() {
        Iterable students = studentService.getAll();
        return students;
    }

    //增加
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    //编辑
    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public Student uptade(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

}
