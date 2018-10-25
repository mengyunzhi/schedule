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
    List<Course> findAllByIdIn(List<Long> ids);

   /**
    * @Param: [name]
    * @return: java.util.List<com.mengyunzhi.schedule.entity.Course>
    * @Author: liyiheng
    * @Date: 10/25/2018
    * @Description:
    * 通过课程名查找课程
    */
   List<Course> findByNameLike(String name);

   /**
    * @Param: [semester]
    * @return: java.util.List<com.mengyunzhi.schedule.entity.Course>
    * @Author: liyiheng
    * @Date: 10/25/2018
    * @Description:
    * 通过学期找课程
    */
   List<Course> findBySemester(Semester semester);
}
