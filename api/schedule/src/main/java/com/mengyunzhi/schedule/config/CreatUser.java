package com.mengyunzhi.schedule.config;

import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 在启动时添加一个用户
 * 方便注册
 */
@Component
public class CreatUser implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userRepository.save(user);
    }
}
