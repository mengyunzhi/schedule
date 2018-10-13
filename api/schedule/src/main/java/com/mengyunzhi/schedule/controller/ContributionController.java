package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.service.ContributionService;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/10
 * 贡献值
 */
@RestController
@RequestMapping("/Contribution")
public class ContributionController {
    @Autowired
    ContributionService contributionService;
    @Autowired
    StudentService studentService;

    //找到id的所属对象
    @GetMapping("/{id}")
    public Contribution getById(@PathVariable Long id) {
        return contributionService.getById(id);
    }

    /**
     * @return void
     * @param: [contribution, id]
     * @author liyiheng
     * @description 修改某个人的贡献值
     */
    @PutMapping("/modify/{id}")
    public void modifyContribution(@RequestBody Contribution contribution, @PathVariable Long id) {
        contributionService.modifyContribution(contribution, id);
    }

    /**
     * @return List<contribution>
     * @param: [id]
     * @author liyiheng
     * @description 得到贡献值的详细来源
     */
    @GetMapping("/information/{id}")
    public List<Contribution> getDetailedInformation(@PathVariable Long id) {
        return contributionService.getDetailedInformation(id);
    }
    
}
