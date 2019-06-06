package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.SystemSetting;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: htx
 * @date: 19-6-5
 */
public interface SystemSettingRepository extends CrudRepository<SystemSetting, Long> {
    SystemSetting findOneByKey(String ddUrl);
}
