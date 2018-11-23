package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    /**
     * @return com.mengyunzhi.schedule.entity.Student
     * @param: [github]
     * @author liyiheng
     * @description 通过github名找到对象
     */
    Student findByGithub(String github);

    /**
     * 返回有关课程的所有激活学生
     *
     * @param courses 有关课程
     * @return 有关课程的所有激活学生
     */
    Set<Student> findByStateAndCourseListIn(boolean state ,List<Course> courses);

    /**
     * 通过名字找到学生
     *
     * @param name
     * @return
     */
    List<Student> findByName(String name);

    /**
     * 按学生姓名查询
     * @param name
     * @return
     */
    List<Student> findByNameLike(String name);

    /**
     * 通过学生的状态来返回学生
     * @param state
     * @return
     */
    List<Student> findByState(boolean state);
}
