package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Contribution;

import java.util.List;

/**
 * @author liyiheng
 * @date 2018/10/10 15:18
 */
public interface ContributionService {
    Contribution getById(Long id);
    void modifyContribution(Contribution contribution, Long id);
    List<Contribution> getDetailedInformation(Long id);
    int weekIncrease(Long id);
    int monthIncrease(Long id);
}
