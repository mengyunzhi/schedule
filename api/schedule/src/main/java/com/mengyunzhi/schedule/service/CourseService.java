package com.mengyunzhi.schedule.service;


import com.mengyunzhi.schedule.entity.Course;
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

    /**
     * @Param: [name]
     * @return: java.util.List<com.mengyunzhi.schedule.entity.Course>
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description:
     * 通过课程名找课程
     */
    List<Course> findCourseByName(String name);

    /**
     * @Param: [id]
     * @return: java.util.List<com.mengyunzhi.schedule.entity.Course>
     * @Author: liyiheng
     * @Date: 10/25/2018
     * @Description:
     * 通过学期id，找到本学期的课程
     */
    List<Course> findCourseBySemesterId(Long id);
}
