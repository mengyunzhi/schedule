package com.mengyunzhi.schedule.dataInit;

import com.mengyunzhi.schedule.entity.SystemSetting;
import com.mengyunzhi.schedule.repository.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author: htx
 * @date: 19-6-5
 */
@Component
public class SystemSettingDataInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SystemSettingRepository systemSettingRepository;

    List<SystemSetting> defaultSetting = Arrays.asList(
            new SystemSetting("ddUrl", "")
    );  //  默认系统设置

    /**
     * @description  初始化系统设置
     * @param contextRefreshedEvent
     * @return void
     * @author htx
     * @date 下午12:19 19-6-5
     **/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        systemSettingRepository.save(defaultSetting);
    }
}
