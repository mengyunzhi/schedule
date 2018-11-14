package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StudentServiceImplTest extends ServiceTest {
    private final static Logger logger = Logger.getLogger(StudentRepository.class.getName());
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
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
    public void studentNameIsExist() {
        Student student1 = new Student();
        student1.setName("studentTest");
        studentRepository.save(student1);

        boolean result = studentService.studentNameIsExist("studentTest");
        assertThat(result).isTrue();
        assertThat(studentService.studentNameIsExist("test")).isFalse();
    }

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
        zhangsan.setGroups("朴世超小组");
        studentRepository.save(zhangsan);

        //新建一个李四学生
        Student lisi = new Student();
        lisi.setName("李四");
        lisi.setPhoneNumber("789456123");
        lisi.setContributionCoefficient('2');
        lisi.setGithub("lisi");
        lisi.setGroups("张喜硕小组");

        //用李四的信息更新张三的信息
        studentService.update(zhangsan.getId(), lisi);

        //断言更新成功
        Student newStudent = studentRepository.findOne(zhangsan.getId());
        assertThat(newStudent.getName()).isEqualTo("李四");
        assertThat(newStudent.getPhoneNumber()).isEqualTo("789456123");
        assertThat(newStudent.getContributionCoefficient()).isEqualTo('2');
        assertThat(newStudent.getGithub()).isEqualTo("lisi");
        assertThat(newStudent.getGroups()).isEqualTo("张喜硕小组");
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

    //选课   测试
    @Test
    public void selectCourse() {
        //新建一个数组链表
        List<Course> courseList = new ArrayList<>();

        //新建俩门课程，并持久化
        Course math = new Course();
        Course physics = new Course();
        courseRepository.save(math);
        courseRepository.save(physics);

        //保存课程到数组链表
        courseList.add(math);
        courseList.add(physics);

        //新建一个学生
        Student zhangsan = new Student();
        studentRepository.save(zhangsan);

        //调用selectCourse方法进行选课
        studentService.selectCourse(zhangsan.getId(), courseList);

        //断言选课成功
        Student newStudent = studentRepository.findOne(zhangsan.getId());
        assertThat(newStudent.getCourseList()).isEqualTo(courseList);
    }

    // 删除 测试
    @Test
    public void delete() {
        // 新建一个学生
        Student student = new Student();
        studentRepository.save(student);

        // 调用delete方法删除
        studentService.delete(student.getId());

        // 断言删除成功
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent).isNull();
    }

    // 按学生姓名查询 测试
    @Test
    public void getByName() {
        // 新建一个学生
        Student zhangsan = new Student();
        zhangsan.setName("张三");
        studentRepository.save(zhangsan);

        // 调用getByName方法查找学生名称
        studentService.findByNameLike(zhangsan.getName());

        // 断言成功
        Student newStudent = studentRepository.findOne(zhangsan.getId());
        assertThat(newStudent.getName()).isEqualTo(zhangsan.getName());
    }
}