package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.other.PayLoad;
import com.mengyunzhi.schedule.repository.ContributionRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/10 15:19
 */
@Service
public class ContributionServiceImpl implements ContributionService {
    @Autowired
    ContributionRepository contributionRepository;
    @Autowired
    StudentRepository studentRepository;

    //根据id找对象
    @Override
    public Contribution getById(Long id) {
        return contributionRepository.findOne(id);
    }

    //修改contribution
    @Override
    public void modifyContribution(Contribution contribution, Long id) {
        Student student = studentRepository.findOne(id);
        student.setContributionValue(student.getContributionValue() + contribution.getValue());
        contributionRepository.save(contribution);
        List<Contribution> contributionList = student.getContributionList();
        contributionList.add(contribution);
        student.setContributionList(contributionList);
        studentRepository.save(student);
    }



    @Override
    public List<Contribution> getDetailedInformation(Long id) {
        Student student = studentRepository.findOne(id);
        return student.getContributionList();
    }

    /**
     * @return void
     * @param: [payLoad]
     * @author liyiheng
     * @description github提交时自动计算贡献值
     */
    @Override
    public void addContribution(PayLoad payLoad) {

        //工作时间
        float workHours;
        Contribution contribution = new Contribution();
        contribution.setTitle(payLoad.getPull_request().getTitle());

        //取出标题中的数据
        String title = payLoad.getPull_request().getTitle();

        int spacePosition = title.indexOf(" ");   // 题目中空格的位置
        int hPosition = title.indexOf('h');       // h的位置

        workHours = Float.parseFloat(title.substring(spacePosition, hPosition));

        //通过github找到学生
        Student student = studentRepository
                .findByGithub(payLoad.getPull_request()
                        .getUser().getLogin());

        //计算贡献度
        float newContribution;
        newContribution = student.getContributionValue() + student.getContributionCoefficient() * workHours;
        student.setContributionValue(newContribution);

        //构建pull request
        String pullRequest = payLoad.getPull_request().getTitle()
                 + "#" + payLoad.getNumber() + " "   //用空格分割名称和网站
                + payLoad.getPull_request().getUrl();

        contribution.setPullRequest(pullRequest);
        //设置备注
        contribution.setRemarks(payLoad.getBody());

        //设置时间
        Date pullRequestTime = payLoad.getPull_request().getCreated_at().getTime();
        contribution.setTime(pullRequestTime.getTime());

        //贡献属于那个学生
        contribution.setStudent(student);

        //保存数据
        studentRepository.save(student);
        contributionRepository.save(contribution);
    }
}
