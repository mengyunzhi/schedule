package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.SystemSetting;
import com.mengyunzhi.schedule.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: htx
 * @date: 19-6-5
 */
@RestController
@RequestMapping("/SystemSetting")
public class SystemSettingController {

    @Autowired
    SystemSettingService systemSettingService;

    /**
     * @description  获取所有系统设置
     * @param
     * @return java.util.List<com.mengyunzhi.schedule.entity.SystemSetting>
     * @author htx
     * @date 下午12:19 19-6-5
     **/
    @GetMapping
    public List<SystemSetting> getAll() {
        return systemSettingService.getAll();
    }

    /**
     * @description  更新系统设置
     * @param systemSettings
     * @return void
     * @author htx
     * @date 下午12:19 19-6-5
     **/
    @PutMapping("/updateSettings")
    public void updateSettings(@RequestBody List<SystemSetting> systemSettings) {
        systemSettingService.updateSettings(systemSettings);
    }

}
