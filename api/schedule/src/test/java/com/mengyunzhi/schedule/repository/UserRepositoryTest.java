package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends ScheduleRepositoryTest {

    private final static Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class.getName());

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        logger.debug("实例化一个对象");
        User newUser = new User();
        newUser.setUsername("owpKyaRMPMPMvYM4qKst5poV3LN2AfP4");
        newUser.setPassword("fULN5EyT5ioPrikWVU7mNA89W61flBkF");
        userRepository.save(newUser);
        logger.debug("通过用户名查找对象，并断言");
        User user = userRepository.findByUsername(newUser.getUsername());
        Assertions.assertThat(user).isNotNull();
    }
}