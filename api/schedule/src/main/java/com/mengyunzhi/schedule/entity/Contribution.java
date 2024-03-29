package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;

import javax.persistence.*;

/**
 * @author liyiheng
 * @date 2018/10/9 16:51
 * 贡献值
 */
@Entity
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonView({View.Contribution.class, View.Student.class})
    private float value;

    //备注
    @JsonView(View.Contribution.class)
    private String remarks;

    @JsonView({View.Contribution.class, View.Student.class})
    private long time;

    @JsonView(View.Contribution.class)
    private String title;

    @JsonView(View.Contribution.class)
    private String pullRequest;

    public Contribution() {
    }

    //多个贡献度对应一个学生
    @ManyToOne
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(String pullRequest) {
        this.pullRequest = pullRequest;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
