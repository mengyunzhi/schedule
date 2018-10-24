package com.mengyunzhi.schedule.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;

import javax.persistence.*;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/9 17:08
 * 学期
 */
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Semester.class, CourseJsonView.class})
    private Long id;

    //本学期的终止时间
    @JsonView(value = View.Semester.class)
    private String endTime;

    @JsonView({CourseJsonView.class, View.Semester.class})
    private String name;

    //本学期的开始时间
    @JsonView(value = View.Semester.class)
    private String startTime;

    //该学期所处的情况 默认为不激活
    @JsonView(value = View.Semester.class)
    private boolean status;

    @OneToMany(mappedBy = "semester")
    @JsonView(View.Semester.class)
    List<Schedule> schedules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
