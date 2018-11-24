package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;
import com.mengyunzhi.schedule.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 学期管理
 * htxiang
 */

@RestController
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    SemesterService semesterService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.Semester.class)
    public Semester add(@RequestBody Semester semester) {
        return semesterService.add(semester);
    }

    @GetMapping("/")
    @JsonView(View.Semester.class)
    public Iterable<Semester> getAll() {
        Iterable<Semester> semesters = semesterService.getAll();
        return semesters;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        semesterService.delete(id);
    }

    @PutMapping("/active/{id}")
    public void activeSemester(@PathVariable Long id) {
        semesterService.activeSemester(id);
    }

    @PutMapping("/{id}")
    @JsonView(View.Semester.class)
    public Semester update(@PathVariable Long id, @RequestBody Semester semester) {
        return semesterService.update(id, semester);
    }

    @GetMapping("/{id}")
    @JsonView(View.Semester.class)
    public Semester getById(@PathVariable Long id) {
        return semesterService.getById(id);
    }

    @GetMapping("/name/{name}")
    @JsonView(View.Semester.class)
    public List<Semester> getByName(@PathVariable String name) {
        return semesterService.getByName(name);
    }

    /**
     * 根据学期名获的分页数据
     * @param name  学期名
     * @param page  当前页
     * @param size  分页大小
     * @return 分页数据
     */
    @GetMapping("/pageByName/{name}/{page}/{size}")
    @JsonView(View.Semester.class)
    public Page<Semester> getByNameAndPage(@PathVariable String name ,@PathVariable int page, @PathVariable int size) {
        PageRequest pageable = new PageRequest(page, size);
        return semesterService.pageByName(name, pageable);
    }

    @GetMapping("/currentSemester")
    @JsonView(View.Semester.class)
    public Semester getCurrentSemester() {
        return semesterService.currentSemester();
    }

    /**
     * 获取激活学期
     * return {Semester}
     * @author     chenjie
     */
    @GetMapping("/getSemester")
    @JsonView(View.Semester.class)
    public Semester getSemester() {
        return semesterService.getSemester();
    }

    /**
     * 获取分页学期
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page/{page}/{size}")
    @JsonView(View.Semester.class)
    public Page<Semester> page(@PathVariable int page, @PathVariable int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Semester> semesterPage = semesterService.page(pageRequest);
        return semesterPage;
    }
}
