package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    /**
     * 通过学期和周次来查找行程
     * @param semester  学期
     * @param weekOrder 周次
     * @return          查找到的学期
     */
    List<Schedule> findBySemesterAndWeekOrder(Semester semester, int weekOrder);

    /**
     * 根据学期来查找行程
     * @param semester  学期
     * @return          查找到的行程
     */
    List<Schedule> findBySemester(Semester semester);

    /**
     * 通过学期和周次获取行程并通过星期排序
     * @param semester
     * @param weekOrder
     * @return
     */
    List<Schedule> findBySemesterAndWeekOrderOrderByWeekAscNodeAsc(Semester semester, int weekOrder);

    /**
     * 通过学期和周次和星期获取行程 并按照节次排序
     * @param semester
     * @param weekOrder
     * @param week
     * @return
     */
    List<Schedule> findBySemesterAndWeekOrderAndWeekOrderByNodeAsc(Semester semester, int weekOrder, int week);

    /**
     * 通过星期、节次、周次、学期ID 获取行程
     * @param week 星期
     * @param node 节次
     * @param weekOrder 周次
     * @param semesterId 学期ID
     * @return
     */
    Schedule findByWeekAndNodeAndWeekOrderAndSemesterId(int week, int node, Integer weekOrder, Long semesterId);
    Schedule findByWeekAndNodeAndWeekOrder(int week, int node, Integer weekOrder);

    /**
     * 通过星期和节次获取行程
     * @param week  星期
     * @param node  节次
     * @return
     */
    List<Schedule> findByWeekAndNode(int week, int node);
}
