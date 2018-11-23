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
     *
     * @param courseList
     * @author poshichao
     */
    void deleteAll(List<Course> courseList);

    Page<Course> page(Pageable pageable);

    Course getById(Long id);

    /**
     * 更新
     *
     * @param id     更新实体的iD
     * @param course 更新的内容
     * @author chenjie
     */
    void updateByIdAndCourse(long id, Course course);

    /**
     * @Param: [id, name]      接收学期id，查询的课程的名字
     * @return: List<Course>   返回一个List<Course>
     * @Author: liyiheng
     * @Date: 10/26/2018
     * @Description: 通过学期和课程名查找课程
     */
    List<Course> findCourseByNameAndSemesterId(Long id, String name);

    // 为课程选择时间
    void selectCourseBySchedule(Long id, int week, int node,Long semesterId, List<Integer> weekOrders);

    // 获取当前激活学期的课程
    List<Course> getActiveSemesterByCourse();

}
