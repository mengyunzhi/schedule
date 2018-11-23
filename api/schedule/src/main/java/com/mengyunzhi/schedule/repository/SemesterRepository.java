package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SemesterRepository extends PagingAndSortingRepository<Semester, Long> {
    /**
     * 通过学期的名字来获得学期
     * @param name  学期的名字
     * @return      找到的学期集合
     */
    List<Semester> findByName(String name);

    /**
     * 根据状态获得学期
     * @param status
     * @return
     */
    List<Semester> findByStatus(boolean status);

    Page<Semester> findAllByName(String name, Pageable pageable);
}
