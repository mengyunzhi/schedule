package com.mengyunzhi.schedule.service;


import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 课程
 * @author chenjie
 */
public interface CourseService {
    Course save(Course course);

    Iterable<Course> getAll();

    /**
     * 课程批量删除
     * @param courseList
     * @author poshichao
     */
    void deleteAll(List<Course> courseList);

    Page<Course> page(Pageable pageable);

    Course getById(Long id);

    /**
     * 更新
     * @param id 更新实体的iD
     * @param course 更新的内容
     * @author chenjie
     */
    void updateByIdAndCourse(long id, Course course);

    // 为课程选择时间
    void selectCourseBySchedule(Long id, List<Schedule> schedules);
}
