package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
 * @author chenjie
 * 课程
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
