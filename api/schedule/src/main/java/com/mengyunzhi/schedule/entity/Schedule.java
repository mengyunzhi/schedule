package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/9 17:32
 * 时间
 */
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Schedule.class, View.Semester.class, CourseJsonView.class})
    private Long id;

    //第几节课
    @JsonView({View.Schedule.class, View.Semester.class, CourseJsonView.class})
    private int node;

    @ManyToOne
    private Semester semester;

    @ManyToMany(mappedBy = "scheduleList")
    @JsonView({View.Schedule.class})
    private List<Course> courseList = new ArrayList<Course>();

    //星期
    @JsonView({View.Schedule.class, View.Semester.class, CourseJsonView.class})
    private int week;

    //周次
    @JsonView({View.Schedule.class, View.Semester.class, CourseJsonView.class})
    private int weekOrder;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }


    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeekOrder() {
        return weekOrder;
    }

    public void setWeekOrder(int weekOrder) {
        this.weekOrder = weekOrder;
    }
}
