package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Semester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SemesterRepositoryTest {
    @Autowired SemesterRepository semesterRepository;
    @Test
    public void findByName() {
        String name = "name";
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        Semester semester = new Semester();
        semester.setName(name);
        semesters.add(semester);

        Semester semester1 = new Semester();
        semester1.setName(name);
        semesters.add(semester1);

        semesterRepository.save(semesters);

        List<Semester> findSemesters = semesterRepository.findByName(name);
        for (Semester item:
             findSemesters) {
            assertThat(item.getName()).isEqualTo(name);
        }
    }
}