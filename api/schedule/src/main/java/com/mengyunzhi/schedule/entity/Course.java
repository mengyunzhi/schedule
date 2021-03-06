package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/9 17:04
 * 课程
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Schedule.class, CourseJsonView.class, View.Student.class, View.CoursePageJsonView.class})
    private Long id;

    @JsonView({View.Schedule.class, CourseJsonView.class, View.Student.class, View.CoursePageJsonView.class})
    private String name;

    @ManyToOne
    @JsonView({CourseJsonView.class})
    private Semester semester;

    @ManyToMany
    @JsonView(CourseJsonView.class)
    private List<Schedule> scheduleList = new ArrayList<>();;

    @ManyToMany(mappedBy = "courseList")
    @JsonView({View.Schedule.class})
    private List<Student> studentList;

    public Course() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

}
