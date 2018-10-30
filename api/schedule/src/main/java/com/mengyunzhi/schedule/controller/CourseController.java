package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.JsonPage;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;
import com.mengyunzhi.schedule.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @JsonView(CourseJsonView.class)
    @GetMapping("/page")
    public Iterable<Course> page(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Course> coursePage = courseService.page(pageRequest);
        return new JsonPage(coursePage.getContent(), pageRequest, coursePage.getSize());
    }

    // 获取单个课程
    @JsonView(CourseJsonView.class)
    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    // 更新课程
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Course course) {
        courseService.updateByIdAndCourse(id, course);

    }

    // 批量删除课程
    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@RequestBody List<Course> courseList) {
        courseService.deleteAll(courseList);
    }

    /**
     * @Param: [id, name]      接收学期id，查询的课程的名字
     * @return: List<Course>   返回一个List<Course>
     * @Author: liyiheng
     * @Date: 10/26/2018
     * @Description: 通过学期和课程名查找课程
     */
    @GetMapping("query")
    public List<Course> findCourseByNameAndSemesterId(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {

        //如果学期为空，返回空
        if (id == null)
            return null;
        return courseService.findCourseByNameAndSemesterId(id, name);
    }

    // 为课程选择时间
    @PutMapping("/select/{id}")
    public void selectCourseBySchedule(@PathVariable Long id,@RequestParam int week, @RequestParam int node, @RequestParam List<Integer> weekOrders) {
       courseService.selectCourseBySchedule(id, week, node, weekOrders);
    }

}
