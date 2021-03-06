package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @JsonView(View.Student.class)
    @GetMapping
    public Iterable<Student> getAll() {
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
    public boolean githubIsExist(@RequestParam String github) {
        return studentService.githubIsExist(github);
    }

    @JsonView(View.Student.class)
    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    //编辑
    @JsonView(View.Student.class)
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    //改变当前学生状态
    @PutMapping("/state/{id}")
    @JsonView(View.Student.class)
    public Student changeState(@PathVariable Long id) {
        return studentService.changeState(id);
    }

    //选课
    @JsonView({View.Student.class})
    @PutMapping("/select/{id}")
    public void selectCourse(@PathVariable Long id, @RequestBody List<Course> courses) {
        studentService.selectCourse(id, courses);
    }

    //删除
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    //按学生姓名查询
    @GetMapping("/name/{name}")
    @JsonView(View.Student.class)
    public List<Student> findByNameLike(@PathVariable String name) {
        return studentService.findByNameLike("%" + name + "%");
    }

    // 按分页信息查询
    @GetMapping("/query/{name}")
    @JsonView(View.Student.class)
    public Page<Student> findByNameLike(@PathVariable String name,@RequestParam int page ,@RequestParam int size) {
        PageRequest pageRequest =new PageRequest(page,size);
        return studentService.findByNameLike("%" + name + "%", pageRequest);
    }

    /**
     * 获得激活的学生
     * @return
     */
    @GetMapping("/getActiveStudents")
    @JsonView(View.Student.class)
    public List<Student> getActiveStudents()
    {
        return studentService.getActiveStudent();
    }

    //分页
    //page?page=0&size=1
    @GetMapping("/page")
    @JsonView(View.Student.class)
    public Iterable<Student> page(@RequestParam int page ,@RequestParam int size){
        PageRequest pageRequest =new PageRequest(page,size);
        return studentService.page(pageRequest);
    }
}

