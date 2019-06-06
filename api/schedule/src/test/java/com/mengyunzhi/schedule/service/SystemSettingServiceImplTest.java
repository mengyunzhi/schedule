package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.SystemSetting;
import com.mengyunzhi.schedule.repository.SystemSettingRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author: htx
 * @date: 19-6-5
 */
public class SystemSettingServiceImplTest extends ServiceTest {

    private final static Logger logger = Logger.getLogger(SystemSettingServiceImplTest.class.getName());

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @Test
    public void updateSetting() {
        logger.info("创建设置");
        SystemSetting systemSetting = new SystemSetting();
        systemSetting.setKey("name");
        systemSetting.setValue("test1");

        systemSettingRepository.save(systemSetting);
        logger.info("更新设置");
        systemSetting.setValue("test2");
        systemSettingService.updateSettings(Collections.singletonList(systemSetting));

        logger.info("断言更新成功");
        assertThat(systemSettingRepository.findOneByKey(systemSetting.getKey()).getValue()).isEqualTo("test2");

    }

}