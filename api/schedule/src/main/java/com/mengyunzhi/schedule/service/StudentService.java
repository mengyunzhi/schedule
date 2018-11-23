package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Iterable<Student> getAll();

    Student save(Student student);

    /**
     * 返回有关课程的所有激活学生
     *
     * @param courses 有关课程
     * @return 所有有课的激活的学生
     */
    Set<Student> findByCoursesIn(List<Course> courses);

    /**
     * 判断名字是否存在与学生中
     */
    boolean studentNameIsExist(String name);

    /**
     * 判断github名字是否存在学生之中
     *
     * @param github
     * @return
     */
    boolean githubIsExist(String github);

    Student getById(Long id);

    Student update(Long id, Student student);

    /**
     * 改变学生状态
     *
     * @param id 该学生
     * @return 学生状态
     */
    Student changeState(Long id);

    //选课
    void selectCourse(Long id, List<Course> courses);

    //删除
    void delete(Long id);

    //按学生姓名查询
    List<Student> findByNameLike(String name);

    /**
     * 获得激活状态的学生
     * @return
     */
    List<Student> getActiveStudent();
}
