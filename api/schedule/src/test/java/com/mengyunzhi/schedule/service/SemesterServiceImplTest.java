package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.Semester;
import com.mengyunzhi.schedule.repository.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class SemesterServiceImplTest extends ServiceTest{
    @Autowired SemesterService semesterService;
    @Autowired
    SemesterRepository semesterRepository;
    @Test
    public void add() {
        Semester semester = new Semester();
        semester.setName("semester");

        semester.setStartTime("1514739661000");
        semester.setEndTime("1514739671000");
        semesterService.add(semester);
        assertThat(semester.getName()).isEqualTo("semester");
    }

    @Test
    @Transactional
    public void delete() {
        Semester semester = new Semester();
        semester.setName("test1");
        semester.setStartTime("1514739661000");
        semester.setEndTime("1515862861000");
        semesterService.add(semester);

        Long id = semester.getId();
        semesterService.delete(id);
        assertThat(semesterRepository.findOne(id)).isNull();
    }

    @Test
    @Transactional
    public void getAll() {
        Iterable<Semester> semesters = semesterService.getAll();
    }

    @Test
    public void resetStatus() {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        for (int i = 0; i <= 5 ;i++) {
            Semester semester = new Semester();
            semester.setStatus(true);
            semesters.add(semester);
        }
        semesterRepository.save(semesters);
        semesterService.resetStatus();
        Iterable<Semester> reset = semesterRepository.findAll();
        for (Semester semester :
                reset) {
            assertThat(semester.isStatus()).isFalse();
        }
    }

    @Test
    public void activeSemester() {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        for (int i = 0; i <= 5 ;i++) {
            Semester semester = new Semester();
            semester.setStatus(true);
            semesters.add(semester);
        }
        semesterRepository.save(semesters);

        Semester setSemester = new Semester();
        semesterRepository.save(setSemester);
        semesterService.activeSemester(setSemester.getId());

        Iterable<Semester> reset = semesterRepository.findAll();
        for (Semester semester :
                reset) {
            if (semester.getId() == setSemester.getId()) {
                assertThat(semester.isStatus()).isTrue();
            } else {
                assertThat(semester.isStatus()).isFalse();
            }
        }
    }
}