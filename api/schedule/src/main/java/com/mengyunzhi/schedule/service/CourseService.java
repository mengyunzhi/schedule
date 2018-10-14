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

    void deleteById(List<Long> ids);

    Page<Course> page(Pageable pageable);


    Course getById(Long id);

    /**
     * 更新
     * @param id 更新实体的iD
     * @param course 更新的内容
     * @author chenjie
     */
    void updateByIdAndCourse(long id, Course course);

}
