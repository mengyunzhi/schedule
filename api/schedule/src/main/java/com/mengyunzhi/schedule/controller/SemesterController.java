package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.service.SemesterService;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/semester")
public class SemesterController {
    @Autowired
    SemesterService semesterService;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Schedule.semesterInterface.class)
    public Semester add(@RequestBody Semester semester) {
        return semesterService.add(semester);
    }

    @GetMapping("/")
    @JsonView(Schedule.semesterInterface.class)
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
    @JsonView(Schedule.semesterInterface.class)
    public Semester update(@PathVariable Long id, @RequestBody Semester semester) {
        return semesterService.update(id, semester);
    }

    @GetMapping("/{id}")
    @JsonView(Schedule.semesterInterface.class)
    public Semester getById(@PathVariable Long id) {
        return semesterService.getById(id);
    }

    @GetMapping("/name/{name}")
    @JsonView(Schedule.semesterInterface.class)
    public List<Semester> getByName(@PathVariable String name) {
        return semesterService.getByName(name);
    }
}
