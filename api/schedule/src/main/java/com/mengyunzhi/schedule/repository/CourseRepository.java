package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/*
 * @author chenjie
 * 课程
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    List<Course> findByNameLikeAndSemester(String name, Semester semester);
}
