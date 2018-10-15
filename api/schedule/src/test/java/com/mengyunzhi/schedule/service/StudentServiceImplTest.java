package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StudentServiceImplTest extends ServiceTest {
    private final static Logger logger = Logger.getLogger(StudentRepository.class.getName());
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @Test
    public void save() {
        Student student = new Student();
        studentService.save(student);
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent).isNotNull();
    }

    @Test
    public void getAll() {
        logger.info("新建一个对象");
        Student student = new Student();

        logger.info("调用保存方法");
        studentService.save(student);

        List<Student> StudentList = (List<Student>) studentService.getAll();
        assertThat(StudentList.size()).isNotZero();
    }


    //更新方法测试
    @Test
    public void update() {
        //新建一个张三学生，并持久化
        Student zhangsan = new Student();
        zhangsan.setName("张三");
        zhangsan.setPhoneNumber("123456789");
        zhangsan.setContributionCoefficient('1');
        zhangsan.setGithub("zhangsan");
        studentRepository.save(zhangsan);

        //新建一个李四学生
        Student lisi = new Student();
        lisi.setName("李四");
        lisi.setPhoneNumber("789456123");
        lisi.setContributionCoefficient('2');
        lisi.setGithub("lisi");

        //用李四的信息更新张三的信息
        studentService.update(zhangsan.getId(),lisi);

        //断言更新成功
        Student newStudent = studentRepository.findOne(zhangsan.getId());
        assertThat(newStudent.getName()).isEqualTo("李四");
        assertThat(newStudent.getPhoneNumber()).isEqualTo("789456123");
        assertThat(newStudent.getContributionCoefficient()).isEqualTo('2');
        assertThat(newStudent.getGithub()).isEqualTo("lisi");
    }

    //学生状态方法测试
    @Test
    public void changeState() {
        Student wangwu = new Student();
        wangwu.setState(false);
        studentRepository.save(wangwu);

        Student student = studentService.changeState(wangwu.getId());
        assertThat(student.isState()).isNotEqualTo(wangwu);

    }
}