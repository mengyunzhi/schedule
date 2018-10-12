package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Student getById(Long id) {
        return studentRepository.findOne(id);
    }

    //更新
    @Override
    public Student update(Long id, Student student) {
        Student oldstudent = studentRepository.findOne(id);

        if (oldstudent != null) {
            oldstudent.setName(student.getName());
            oldstudent.setPhoneNumber(student.getPhoneNumber());
            oldstudent.setGithub(student.getGithub());
            oldstudent.setContributionCoefficient(student.getContributionCoefficient());

        }
        return studentRepository.save(oldstudent);
    }
}
