package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Semester;

import java.util.List;

/**
 * 学期服务
 * htxiang
 */


public interface SemesterService {

    //新建学期时　要新建行程
    Semester add(Semester semester);

    //返回所有的学期
    Iterable<Semester> getAll();

    void delete(Long id);
    //将所有学期的状态重置
    void resetStatus();

    //设置一个学期为激活学期
    void activeSemester(Long id);

    //编辑学期
    Semester update(Long id, Semester semester);

    //获得学期
    Semester getById(Long id);

    //通过学期名字来返回学期
    List<Semester> getByName(String name);

    //生成学期的行程
    void creatScheduleOfSemester(Semester semester, int startWeek, int endWeek);

    //删除指定学期和周次的行程
    void deleteScheduleOfSemesterAndWeekorder(Semester semester, int weekOrder);

    //返回学期的总周次
    int getSemesterWeekorder(Semester semester);

    //返回当前时间并激活的学期
    Semester currentSemester();

    // 返回当前激活的学期
    Semester getSemester();

}
