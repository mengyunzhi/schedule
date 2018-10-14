package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Contribution;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author liyiheng
 * @date 2018/10/10 15:16
 * 建立一个访问contribution数据表的接口
 *  * 指名：
 *  * 1. 要操作的为contribution数据
 *  * 2.数据表中主键类型是Long
 */
public interface ContributionRepository extends PagingAndSortingRepository<Contribution, Long> {


}
