package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.other.PayLoad;
import com.mengyunzhi.schedule.repository.ContributionRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liyiheng
 * @date 2018/10/11 17:29
 */

public class ContributionServiceImplTest extends ServiceTest {
    @Autowired
    ContributionRepository contributionRepository;
    @Autowired
    ContributionService contributionService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    public void modifyContribution() {
        //新建一个学生~!
        Student student = new Student();

        //给他一定的贡献值
        student.setContributionValue(111);
        studentService.save(student);
        //初始化一个贡献值类
        Contribution contribution = new Contribution();
        contribution.setValue(11);
        contributionRepository.save(contribution);
        //调用修改贡献值的方法
        contributionService.modifyContribution(contribution, student.getId());

        //断言更新成功
        Student student1 = studentRepository.findOne(student.getId());
        assertThat(student1.getContributionValue()).isEqualTo(122);
    }


    @Test
    public void addContribution() {
        //创建一个PayLoad对象
        PayLoad payLoad = PayLoad.create();
        String github = "123";
        //初始化数据
        payLoad.getPull_request().getUser().setLogin(github);

        payLoad.getPull_request().setTitle("12314141 2.5h");
        Calendar calendar =Calendar.getInstance();
//        Date date = calendar.getTime();
        payLoad.getPull_request().setCreated_at(calendar);


        //创建并保存学生对象
        Student student = new Student();
        student.setGithub(github);
        student.setContributionCoefficient(1);
        student.setContributionValue(123);
        studentRepository.save(student);


        //判断测试方法是否运行成功
        contributionService.addContribution(payLoad);
        assertThat(student.getContributionValue()).isEqualTo(125.5f);

    }
}