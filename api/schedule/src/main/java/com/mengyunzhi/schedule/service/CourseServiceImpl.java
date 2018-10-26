package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjie
 * @date 2018/10/11 19:05
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    CourseRepository courseRepository;  //课程

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Iterable<Course> getAll() {
        return courseRepository.findAll();

    }

    @Override
    public void deleteAll(List<Course> courseList) {
        courseRepository.delete(courseList);
    }

    @Override
    public Page<Course> page(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findOne(id);
    }

    @Override
    public void updateByIdAndCourse(long id, Course newCourse) {
        // 根据传入的ID获取要更新的实体
        Course oldCourse = courseRepository.findOne(id);

        //更新实体的内容
        if (oldCourse != null) {
            oldCourse.setName(newCourse.getName());

            // 持久化更新的实体
            courseRepository.save(oldCourse);
        }
    }

    /**
     * @Param: [id, name]      接收学期id，查询的课程的名字
     * @return: List<Course>   返回一个List<Course>
     * @Author: liyiheng
     * @Date: 10/26/2018
     * @Description: 通过学期和课程名查找课程
     */
    @Override
    public List<Course> findCourseByNameAndSemesterId(Long id, String name) {
        return courseRepository.findByNameLikeAndSemester("%" + name + "%", semesterRepository.findOne(id));
    }

    // 为课程选择时间
    @Override
    public void selectCourseBySchedule(Long id, List<Schedule> schedules) {
        Course course = courseRepository.findOne(id);
        course.setScheduleList(schedules);
        courseRepository.save(course);
    }
}
