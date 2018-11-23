package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
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
     *
     * @param courses 有关课程
     * @return
     */
    @Override
    public Set<Student> findByCoursesIn(List<Course> courses) {
        return studentRepository.findByCourseListIn(courses);
    }

    @Override
    public boolean studentNameIsExist(String name) {
        List<Student> students = studentRepository.findByName(name);
        if (students.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean githubIsExist(String github) {
        Student student = studentRepository.findByGithub(github);
        if (student != null) {
            return true;
        }
        return false;
    }

    public Student getById(Long id) {
        return studentRepository.findOne(id);
    }

    //更新
    @Override
    public Student update(Long id, Student student) {
        Student oldStudent = studentRepository.findOne(id);
        if (oldStudent != null) {
            oldStudent.setName(student.getName());
            oldStudent.setPhoneNumber(student.getPhoneNumber());
            oldStudent.setGithub(student.getGithub());
            oldStudent.setContributionCoefficient(student.getContributionCoefficient());
            oldStudent.setGroups(student.getGroups());
        }
        return studentRepository.save(oldStudent);
    }

    //改变当前学生状态
    @Override
    public Student changeState(Long id) {
        Student oldStudent = studentRepository.findOne(id);

        if (oldStudent != null) {
            oldStudent.setState(!oldStudent.isState());
        }
        return studentRepository.save(oldStudent);
    }

    //选课
    @Override
    public void selectCourse(Long id, List<Course> courses) {
        Student student = studentRepository.findOne(id);
        student.setCourseList(courses);
        studentRepository.save(student);
    }

    //删除
    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }

    //按学生姓名查询
    @Override
    public List<Student> findByNameLike(String name) {
        return studentRepository.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Student> getActiveStudent() {
        return studentRepository.findByState(true);
    }

    //分页
    @Override
    public Page<Student> page(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
