package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjie
 * @date 2018/10/11 19:05
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRespository;  //课程

    @Override
    public Course save(Course course) {
        return courseRespository.save(course);
    }

    @Override
    public Iterable<Course> getAll() {
        return courseRespository.findAll();

    }

    @Override
    public Page<Course> page(Pageable pageable) {
        return courseRespository.findAll(pageable);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        courseRespository.deleteAllByIdIn(ids);
    }

    @Override
    public Course getById(Long id) {
        return courseRespository.findOne(id);
    }

    @Override
    public void updateByIdAndCourse(long id, Course newCourse) {
        // 根据传入的ID获取要更新的实体
        Course oldCourse = courseRespository.findOne(id);

        //更新实体的内容
        if (oldCourse != null) {
            oldCourse.setName(newCourse.getName());

            // 持久化更新的实体
            courseRespository.save(oldCourse);
        }
    }


}
