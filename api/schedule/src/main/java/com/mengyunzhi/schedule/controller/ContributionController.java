package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.other.PayLoad;
import com.mengyunzhi.schedule.service.ContributionService;
import com.mengyunzhi.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @JsonView(View.Contribution.class)
    public List<Contribution> getDetailedInformation(@PathVariable Long id) {
        return contributionService.getDetailedInformation(id);
    }

    /**
     *
     * @param: [payLoad]
     * @return void
     * @author liyiheng
     * @description
     * pull request时自动增加贡献值
     */
    @PostMapping("/github")
    public void addContribution(@RequestBody PayLoad payLoad) {

        contributionService.addContribution(payLoad);
    }

    @GetMapping("/page")
    @JsonView(View.Contribution.class)
    public Page<Contribution> page(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return contributionService.page(pageRequest);
    }



}
