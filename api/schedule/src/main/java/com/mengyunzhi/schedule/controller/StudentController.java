package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    //增加
    @JsonView(View.Student.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/getStudentByCourse")
    @JsonView(View.Student.class)
    public Set<Student> getStudentByCoursesIn(@RequestBody List<Course> courses) {
        return studentService.findByCoursesIn(courses);
    }

    @GetMapping("/nameExist")
    public boolean nameIsExist(@RequestParam String name) {
        return studentService.studentNameIsExist(name);
    }

    @GetMapping("/githubExist")
    public boolean githubIsExist(@RequestParam String github) { return studentService.githubIsExist(github); }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    //编辑
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    //改变当前学生状态
    @PutMapping("/state/{id}")
    public Student changeState(@PathVariable Long id){
        return studentService.changeState(id);
    }

    //选课
    @PutMapping("/select/{id}")
    public void selectCourse(@PathVariable Long id, @RequestBody List<Course> courses) {
        studentService.selectCourse(id, courses);
    }

}

