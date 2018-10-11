package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
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
    private Long id;

    //第几节课
    private int node;

    @ManyToOne
    private Semester semester;

    @ManyToMany
    private List<Course> courseList;

    //星期
    private int week;

    //周次
    private int weekOrder;

    public interface semesterInterface extends Semester.semesterInterface{}

    public Schedule() {
    }

    @JsonView(value = semesterInterface.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(value = semesterInterface.class)
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

    @JsonView(value = semesterInterface.class)
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @JsonView(value = semesterInterface.class)
    public int getWeekOrder() {
        return weekOrder;
    }

    public void setWeekOrder(int weekOrder) {
        this.weekOrder = weekOrder;
    }
}
