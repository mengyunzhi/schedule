package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.ScheduleApplicationTests;
import com.mengyunzhi.schedule.entity.Course;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author chenjie
 * @date 2018/10/11 20:19
 */
public class CourseRepositoryTest extends ScheduleApplicationTests {
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findTest() {
        Iterable<Course> courses = courseRepository.findAll();
        assertThat(courses).isNotNull();
    }
}
