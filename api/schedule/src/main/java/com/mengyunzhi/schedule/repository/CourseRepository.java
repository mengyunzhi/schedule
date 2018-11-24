package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /**
     * 获得与学期有关的所有课程
     * @param semester
     * @return
     */
    List<Course> findBySemester(Semester semester);

    Page<Course> findAllBySemesterId(Long id, Pageable pageable);

    /**
     * 获取分页激活学期的课程
     */
    Page<Course> findAllBySemesterStatus(boolean status, Pageable pageable);
}
