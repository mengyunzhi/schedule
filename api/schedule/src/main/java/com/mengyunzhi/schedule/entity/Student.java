package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;

import javax.persistence.*;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/9 16:42
 * 学生
 */
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Schedule.class, View.Student.class})
    private Long id;

    //贡献值
    private float contributionValue;

    //贡献值系数
    private float contributionCoefficient;

    //一个学生多条贡献值
    @OneToMany
    private List<Contribution> contributionList;

    //一个学生多门课
    @ManyToMany
    private List<Course> courseList;


    @JsonView({View.Schedule.class, View.Student.class})
    private String name;

    private String phoneNumber;

    private String github;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getContributionValue() {
        return contributionValue;
    }

    public void setContributionValue(float contributionValue) {
        this.contributionValue = contributionValue;
    }

    public float getContributionCoefficient() {
        return contributionCoefficient;
    }

    public void setContributionCoefficient(float contributionCoefficient) {
        this.contributionCoefficient = contributionCoefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public List<Contribution> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<Contribution> contributionList) {
        this.contributionList = contributionList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
