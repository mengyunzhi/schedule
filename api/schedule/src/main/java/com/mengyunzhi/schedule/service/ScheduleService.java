package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Schedule;
import org.springframework.http.ResponseEntity;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

    ResponseEntity<String> sendToDD(Date date);

    /**
     * 向钉钉发送课表消息
     * @author chenjie
     */
    ResponseEntity<String> postToDD(String message);

    /**
     * 获取当前日期为当年第几周
     * @author chenjie
     */
    int week_of_year() throws ParseException;

    /**
     * 向钉钉发送随机汇报人
     */
    ResponseEntity<String> randomPush();
}
