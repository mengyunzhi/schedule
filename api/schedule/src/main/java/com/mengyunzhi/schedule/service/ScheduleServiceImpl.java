package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Schedule;
import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.ScheduleRepository;
import com.mengyunzhi.schedule.repository.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    static final long aWeekStamp = (7 * 24 * 60 * 60 * 1000);

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @Autowired
    SemesterService semesterService;

    @Autowired
    StudentService studentService;

    /**
     * 增加行程
     *
     * @param semester  行程的学期
     * @param weekOrder 周次
     * @param node      节次
     * @param week      星期
     * @return 增加的行程
     */
    public Schedule add(Semester semester, int weekOrder, int node, int week) {
        Schedule schedule = new Schedule();
        schedule.setSemester(semester);
        schedule.setWeekOrder(weekOrder);
        schedule.setWeek(week);
        schedule.setNode(node);
        scheduleRepository.save(schedule);
        return schedule;
    }

    /**
     * 批量保存行程
     *
     * @param schedules 要保存的行程
     */
    @Override
    public void saveAll(ArrayList<Schedule> schedules) {
        scheduleRepository.save(schedules);
    }

    /**
     * 批量删除行程
     *
     * @param schedules 要删除的行程
     */
    @Override
    public void delteAll(List<Schedule> schedules) {
        scheduleRepository.delete(schedules);
    }

    /**
     * 根据当前学期和周次并按照节次 周次排序返回行程
     *
     * @param semesterId 当前学期
     * @param weekOrder  当前周次
     * @return
     */
    @Override
    public List<Schedule> getBySemesterAndWeekOrder(Long semesterId, int weekOrder) {
        Semester semester = semesterService.getById(semesterId);
        if (semester == null) {
            return null;
        }
        return scheduleRepository.findBySemesterAndWeekOrderOrderByWeekAscNodeAsc(semester, weekOrder);
    }

    /**
     * 向钉钉发送今日课表信息
     */
    @Override
    public ResponseEntity<String> sendToDD() {
        //学生信息
        List<String> strings = new ArrayList<>();

        //获取今日行程(5个)
        Semester semester = semesterService.currentSemester();
        if (semester == null) {
            return null;
        }
        Date date = new Date();
        Long nowTime = date.getTime();
        Long startTime = Long.parseLong(semester.getStartTime());
        Long totalTime = nowTime - startTime;
        int weekOrder = (int) (totalTime / aWeekStamp) + 1;
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
        List<Schedule> schedules = scheduleRepository.findBySemesterAndWeekOrderAndWeekOrderByNodeAsc(semester, weekOrder, week);

        //分别获取每个行程的学生
        ArrayList<HashSet<Student>> sets = new ArrayList<HashSet<Student>>();
        for (Schedule schedule :
                schedules) {
            HashSet<Student> hashSet = new HashSet<>();
            List<Course> courses = schedule.getCourseList();
            hashSet = (HashSet<Student>) studentService.findByCoursesIn(schedule.getCourseList());
            sets.add(hashSet);
        }

        //获得所有的学生
        String tableHead = formatString("", 32, 16) + formatString("一", 32, 7) + formatString("二", 32, 7) + formatString("三", 32, 7) + formatString("四", 32, 7) + formatString("五", 32, 7) + "\n";
        strings.add(tableHead);
        Iterable<Student> students = studentService.getAll();
        for (Student student :
                students) {
            int lenth = student.getName().length();
            String message = formatString(student.getName(), 32, 8);
            for (HashSet<Student> set :
                    sets) {
                if (set.contains(student)) {
                    message += formatString("   " + "--"  + "   ", 32, 8);
                } else {
                    message += formatString("无课", 32, 5);
                }
            }
            message += "\n";
            strings.add(message);
        }
        //发送给钉钉
        String textMsg = "";
        for (String s :
                strings) {
            textMsg += s;
        }
        return postToDD(textMsg);
    }

    /**
     * 将字符串发送到钉钉
     *
     * @param message
     */
    public ResponseEntity<String> postToDD(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String url = systemSettingRepository.findOneByKey("ddUrl").getValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String testMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + message + "\"}}";

        HttpEntity<String> request = new HttpEntity<>(testMsg, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response;
    }

    /**
     * 获取当前日期为当年第几周
     *
     * @author chenjie
     */
    @Override
    public int week_of_year() {
        // 获取当前日期
        int year, month, day;
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
        String today = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
        // 获取当前周次
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 随机推送每周汇报人
     */
    public ResponseEntity<String> randomPush() {
        // 将学生分组
        List<Student> allStudent = studentService.getActiveStudent();
        List<Student> group1 = new ArrayList<Student>();
        List<Student> group2 = new ArrayList<Student>();

        for (Student student : allStudent
        ) {
            String s = student.getGroups();
            if (student.getGroups().equals("groupPo") && !student.getName().equals("朴世超")) {
                group1.add(student);
            } else if (student.getGroups().equals("groupZhang") && !student.getName().equals("张喜硕")) {
                group2.add(student);
            }
        }
        Random random = new Random();
        Student unLuckGuy = new Student();
        // 如果是单周，则从朴世超小组随机抽取一个人
        int weekOfYear = this.week_of_year();
        if (weekOfYear % 2 != 0) {
            int index = random.nextInt(group1.size());
            unLuckGuy = group1.get(index);
        } else {
            int index = random.nextInt(group2.size());
            unLuckGuy = group1.get(index);
        }

        // 向钉钉发送消息
        String message = "第" + String.valueOf(weekOfYear) + "周随机汇报人是：" + unLuckGuy.getName();
        return postToDD(message);
    }

    /**
     * 格式化字符串
     * @param string    字符串
     * @param ascii     填充的字符
     * @param length    长度
     * @return          格式化后的字符串
     */
    private String formatString(String string, int ascii, int length) {
        String chinese = "[\u0391-\uFFE5]";
        int sLenth = 0;
        // 计算字符串长度
        for (int i = 0; i < string.length(); i++) {
            String temp = string.substring(i, i + 1);
            if (temp.matches(chinese)) {
                sLenth += 2;
            } else {
                sLenth += 1;
            }
        }
        //填充
        if (sLenth > length) {
            return string.substring(0, length);
        } else {
            String s = new String(string);
            String ass = Character.toString((char)ascii);
            for (int i = sLenth; i < length; i ++) {
                s += ass;
            }
            return s;
        }
    }
}
