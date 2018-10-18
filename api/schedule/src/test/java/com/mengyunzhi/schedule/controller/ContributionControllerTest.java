package com.mengyunzhi.schedule.controller;

import com.mengyunzhi.schedule.entity.Contribution;
import com.mengyunzhi.schedule.entity.Student;
import com.mengyunzhi.schedule.repository.ContributionRepository;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author liyiheng
 * @date 2018/10/10 18:50
 */
public class ContributionControllerTest extends ControllerTest {
    //初始化一些需要的数据
    //private final static Logger logger = Logger.getLogger(ContributionControllerTest.class.getName());
    private static final String url = "/Contribution/";

    @Autowired
    ContributionRepository contributionRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    StudentRepository studentRepository;

    @Test
    public void getById() throws Exception {
        //持久化一个实体到数据表
        Contribution contribution = new Contribution();
        //为其设置一个值
        contribution.setValue(11);
        contributionRepository.save(contribution);

        //获取这个实体
        String getUrl = url + contribution.getId().toString();

        this.mockMvc
                .perform(get(getUrl)
                        .header("content-type", MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value(11));
    }

    @Test
    public void modifyContribution() throws Exception {
        //新建一个学生
        Student student = new Student();

        //给他一定的贡献值
        student.setContributionValue(111);
        studentRepository.save(student);
        //初始化一个贡献值类
        Contribution contribution = new Contribution();
        contribution.setValue(11);
        contributionRepository.save(contribution);
        //调用修改贡献值的方法
        String getUrl = url + "modify/" + student.getId().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", contribution.getValue());

        this.mockMvc
                .perform(put(getUrl)
                        .content(jsonObject.toString())
                        .header("content-type", MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getDetailedInformation() throws Exception {
        //初始化学生对象
        Student student = new Student();
        studentRepository.save(student);
        //初始化几个贡献值
        Contribution contribution1 = new Contribution();
        contribution1.setTitle("1");
        contributionRepository.save(contribution1);
        Contribution contribution2 = new Contribution();
        contribution1.setTitle("2");
        contributionRepository.save(contribution2);
        Contribution contribution3 = new Contribution();
        contribution1.setTitle("3");
        contributionRepository.save(contribution3);

        //查找对象
        String getUrl = url + "information/" + student.getId().toString();

        this.mockMvc
                .perform(get(getUrl)
                        .header("content-type", MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}