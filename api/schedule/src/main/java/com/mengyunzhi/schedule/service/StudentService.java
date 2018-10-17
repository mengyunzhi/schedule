package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Iterable<Student> getAll();

    Student save(Student student);

    /**
     * 返回有关课程的所有学生
     * @param courses   有关课程
     * @return          所有的学生
     */
    Set<Student> findByCoursesIn(List<Course> courses);

    Student getById(Long id);

    Student update(Long id, Student student);

    /**
     * 改变学生状态
     * @param id 该学生
     * @return 学生状态
     */
    Student changeState(Long id);

    //选课
    void selectCourse(Long id, List<Course> courses);

}
