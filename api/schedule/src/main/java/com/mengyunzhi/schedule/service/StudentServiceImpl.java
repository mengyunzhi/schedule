package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements  StudentService{
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * 返回有关课程的所有学生
     * @param courses   有关课程
     * @return
     */
    @Override
    public Set<Student> findByCoursesIn(List<Course> courses) {
        return studentRepository.findByCourseListIn(courses);
    }
}
