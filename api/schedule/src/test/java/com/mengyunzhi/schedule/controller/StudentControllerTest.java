package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Course;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.CourseRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

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
    CourseRepository courseRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        this.mockMvc
                .perform(get(url)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        this.mockMvc
                .perform(post(url)
                        .cookie(this.cookie)
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
                        .cookie(this.cookie)
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
                        .cookie(this.cookie)
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
    public void changeStateTest() throws Exception {
        Student student = new Student();
        studentRepository.save(student);

        String putUrl = url + "/state/" + student.getId().toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                //.andDo(print())
                .andExpect(status().isOk());

        //断言为true
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent.isState()).isEqualTo(true);

    }

    //选课 测试
    @Test
    public void selectCourseTest() throws Exception {

        //新建一个数组链表
        List<Course> courseList = new ArrayList<>();

        //新建俩门课程，并持久化
        Course math = new Course();
        Course physics = new Course();
        courseRepository.save(math);
        courseRepository.save(physics);

        //保存课程到数组链表
        courseList.add(math);
        courseList.add(physics);

        //新建一个学生
        Student student = new Student();

        //为学生选择这俩门课程
        student.setCourseList(courseList);

        //保存到数据表
        studentRepository.save(student);

        //将Json对象转换成Json数组
        JSONArray jsonArray = JSONArray.fromObject(courseList);

        String putUrl = url + "/select/" + student.getId().toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(putUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonArray.toString()))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    //删除 测试
    @Test
    public void deleteTest() throws Exception {
        Student student = new Student();
        studentRepository.save(student);
        String deleteUrl = url + student.getId().toString();
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete(deleteUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
        //断言删除成功
        Student newStudent = studentRepository.findOne(student.getId());
        assertThat(newStudent).isNull();
    }

    // 按学生姓名查询   测试
    @Test
    public void getByNameTest() throws Exception {
        // 新建一个学生
        Student zhangsan = new Student();
        zhangsan.setName("张三");
        studentRepository.save(zhangsan);

        String getUrl = url + "/name/" + zhangsan.getName();

        // 断言成功
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(getUrl)
                        .cookie(this.cookie)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
