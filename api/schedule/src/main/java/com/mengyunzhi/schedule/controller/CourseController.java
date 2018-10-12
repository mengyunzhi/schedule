package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjie
 * @date 2018/10/11 19:50
 */
@RestController
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    CourseService courseService;

    // 增加
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Course save(@RequestBody Course course) {
        return courseService.save(course);
    }

    // 获取全部课程
    @GetMapping("/")
    public Iterable<Course> getAll() {
        return courseService.getAll();
    }

    // page?page=0&size=1
    @GetMapping("/page")
    public Iterable<Course> page(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return courseService.page(pageRequest);
    }

    // 获取单个课程
    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    // 更新课程
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Course course) {
        courseService.updateByIdAndCourse(id, course);

    }

//    // 删除课程
//    @DeleteMapping("")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long id) {
//        courseService.delete(id);
//    }
//    @PostMapping("/deleteAll")
//    public void deleteAllById(@RequestBody List<Long> ids) {
//        courseService.deleteAllById(ids);
//    }
}
