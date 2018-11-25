package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JsonView(View.Student.class)
    private float contributionValue;

    //贡献值系数
    @JsonView(View.Student.class)
    private float contributionCoefficient;

    //一个学生多条贡献值
    @OneToMany
    @JsonView({View.Contribution.class, View.Student.class})
    private List<Contribution> contributionList = new ArrayList<>();

    //一个学生多门课
    @ManyToMany
    @JsonView(View.Student.class)
    private List<Course> courseList;

    @JsonView({View.Schedule.class, View.Student.class})
    private String name;

    @JsonView(View.Student.class)
    private String phoneNumber;

    @JsonView(View.Student.class)
    private String github;

    //学生状态显示 默认为false
    @JsonView(View.Student.class)
    private boolean state;

    // 学生属于的小组
    @JsonView(View.Student.class)
    private String groups;

    public Student() {
    }

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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


    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * 复写两个实体的对象当id相等时即相等
     * @param v
     * @return
     */
    @Override
    public boolean equals(Object v) {
        Student v1 = (Student)v;
        return this.id == v1.getId();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
