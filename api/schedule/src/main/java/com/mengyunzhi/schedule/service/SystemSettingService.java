package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.SystemSetting;

import java.util.List;

/**
 * @author: htx
 * @date: 19-6-5
 */
public interface SystemSettingService {

    /**
     * @description  更新系统设置
     * @param systemSettingList
     * @return void
     * @author htx
     * @date 下午12:20 19-6-5
     **/
    void updateSettings(List<SystemSetting> systemSettingList);

    /**
     * @description  获取所有设置
     * @param
     * @return java.util.List<com.mengyunzhi.schedule.entity.SystemSetting>
     * @author htx
     * @date 下午12:21 19-6-5
     **/
    List<SystemSetting> getAll();

}
