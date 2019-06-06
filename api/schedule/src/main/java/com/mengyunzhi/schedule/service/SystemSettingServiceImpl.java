package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.SystemSetting;
import com.mengyunzhi.schedule.repository.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: htx
 * @date: 19-6-5
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @Override
    public void updateSettings(List<SystemSetting> systemSettingList) {
        systemSettingRepository.save(systemSettingList);
    }

    @Override
    public List<SystemSetting> getAll() {
        return (List<SystemSetting>) systemSettingRepository.findAll();
    }

}
