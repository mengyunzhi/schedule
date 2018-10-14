package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends CrudRepository<Student, Long> {

    /**
     * 返回有关课程的所有学生
     * @param courses   有关课程
     * @return          有关课程的所有学生
     */
    Set<Student> findByCourseListIn(List<Course> courses);
}
