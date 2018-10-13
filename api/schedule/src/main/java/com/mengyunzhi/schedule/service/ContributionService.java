package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;

import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/10 15:18
 * contribution的接口服务
 */
public interface ContributionService {
    //通过id找贡献值
    Contribution getById(Long id);

    //修改贡献值
    void modifyContribution(Contribution contribution, Long id);

    /**
     *
     * @param: [id]
     * @return java.util.List<com.mengyunzhi.schedule.entity.Contribution>
     * @author liyiheng
     * @description
     * 找到和某个人相关的所有贡献值
     */
    List<Contribution> getDetailedInformation(Long id);

}
