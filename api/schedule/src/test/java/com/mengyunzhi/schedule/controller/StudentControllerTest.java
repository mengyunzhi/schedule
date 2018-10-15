package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest extends ControllerTest {
    private static final String url = "/student/";

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                //.andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void getByIdTest() throws Exception {
        //持久化一个实体到数据表
        Student student = new Student();

        //为这个学生设置一个随机的字符串
        String name = "张三";
        String phoneNumber = "123456789";
        student.setName(name);
        student.setPhoneNumber(phoneNumber);
        studentRepository.save(student);

        //获取这个持久化的实体
        String getUrl = url + student.getId().toString();

        this.mockMvc
                .perform(get(getUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(phoneNumber));

    }

    //编辑 方法测试
    @Test
    public void updateTest() throws Exception {
        //持久化一个实体到数据表
        Student student = new Student();
        studentRepository.save(student);

        //发起http请求，来更新这个实体
        String newName = "王五";
        String putUrl = url + student.getId().toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"" + newName + "\" }"))
                //.andDo(print())
                .andExpect(status().isOk());

        //断言更新成功
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent.getName()).isEqualTo(newName);
    }

    //学生状态方法测试
    @Test
    public void changeStateTest() throws Exception{
        Student student = new Student();
        studentRepository.save(student);

        String putUrl = url + "/state/" + student.getId().toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                //.andDo(print())
                .andExpect(status().isOk());

        //断言为true
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent.isState()).isEqualTo(true);

    }
}
