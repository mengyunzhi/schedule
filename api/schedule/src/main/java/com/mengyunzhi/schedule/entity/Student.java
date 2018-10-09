package com.mengyunzhi.schedule.entity;

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
    private Long id;

    //贡献值
    private float contributionValue;

    //贡献值系数
    private float contributionCoefficient;

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

    @OneToMany
    private List<Contribution> contributionList;

    @ManyToMany
    private List<Course> courseList;

 }
