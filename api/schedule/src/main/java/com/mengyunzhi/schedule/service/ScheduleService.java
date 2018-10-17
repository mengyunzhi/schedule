package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

public interface ScheduleService {

    /**
     * 只有在新建学期时新建行程
     * @param schedules 要保存的行程
     */
    void saveAll(ArrayList<Schedule> schedules);

    /**
     * 批量删除行程
     * @param schedules 要删除的行程
     */
    void delteAll(List<Schedule> schedules);

    /**
     * 根据当前学期和周次并按照节次 周次排序返回行程
     * @param semesterId  当前学期
     * @param weekOrder 当前周次
     * @return
     */
    List<Schedule> getBySemesterAndWeekOrder(Long semesterId, int weekOrder);

    /**
     * 向钉钉发送课表信息
     */
    ResponseEntity<String> sendToDD();
}
