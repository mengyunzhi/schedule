package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.ContributionRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        studentRepository.save(student);
        contributionRepository.save(contribution);
    }

    @Override
    public List<Contribution> getDetailedInformation(Long id) {
        Student student = studentRepository.findOne(id);
        return student.getContributionList();
    }

//    @Override
//    public int weekIncrease() {
//
//        int weekContribution = 0;
//        Iterable<Student> students = studentRepository.findAll();
//
//        for (Student student :students) {
//
//            List<Contribution> contributionList = this.getDetailedInformation(student.getId());
//            Calendar calendar = Calendar.getInstance();
//
//            //判断本周内的时间
//            int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
//            int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
//            calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
//            Date start = calendar.getTime();
//            long longStart = start.getTime();
//            calendar.add(Calendar.DAY_OF_WEEK, 6); //开始+6，获取周结束日期
//            Date end = calendar.getTime();
//            long longEnd = end.getTime();
//
//            //计算本周贡献度
//            int[] week
//            for (int i = contributionList.size() - 1; i > 0; i--) {
//                Contribution contribution = contributionList.get(i);
//                if (contribution.getTime() >= longStart && contribution.getTime() <= longEnd) {
//                    weekContribution += contribution.getValue();
//                } else {
//                    break;
//                }
//            }
//        }
//        return weekContribution;
//    }
//
//    @Override
//    public int monthIncrease() {
//        int monthContribution = 0;
//
//        //当前是几年，几月
//        Date current = new Date();
//        int currentMonth = current.getMonth();
//        int currentYear = current.getYear();
//
//        //找出本年本月的contribution
//        List<Contribution> contributionList = this.getDetailedInformation(id);
//        for (int i = contributionList.size() - 1; i > 0; i--) {
//            Contribution contribution = contributionList.get(i);
//
//            //该contribution的时间
//            long contributionTime =contribution.getTime();//
//            Date date = new Date(contributionTime);
//            int month = date.getMonth();
//            int year = date.getYear();
//
//            if (currentYear == year && currentMonth == month ) {
//                monthContribution += contribution.getValue();
//            }
//        }
//        return monthContribution;
//    }
}
