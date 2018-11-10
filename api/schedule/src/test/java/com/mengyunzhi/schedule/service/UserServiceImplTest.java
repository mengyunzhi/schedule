package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpSession;

public class UserServiceImplTest extends ScheduleServiceImplTest{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class.getName());
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HttpSession httpSession;

    @Test
    public void loginTest() {
        logger.debug("实例化一个用户");
        User user = new User();
        user.setUsername("InAbxUsHyQ2dXKbWfueZR6WBfH7ZpLLx");
        user.setPassword("LdAUkm37Q5P0oEDLDJtJqKJMJwHaYTmq");
        userRepository.save(user);

        logger.debug("无此用户");
        User testUser = new User();
        user.setUsername("FwypsjUiL0VWSoH3wBgzPqJeii11rX1I");
        Assertions.assertThat(userService.login(testUser)).isFalse();

        logger.debug("密码不正确");
        testUser.setUsername(user.getUsername());
        testUser.setPassword("LdAUkm37Q5P0oEDLDJtJqKJMJwHaYTmq12");
        Assertions.assertThat(userService.login(testUser)).isFalse();

        logger.debug("用户密码正确，断言登录成功，断言成功存入session");
        // 用户名密码正确。断言登录成功，断言成功存入session信息
        testUser.setPassword(user.getPassword());

        //User testUser = userRepository.findByUsername(user.getUsername());
        Assertions.assertThat(userService.login(testUser)).isTrue();
        Assertions.assertThat(testUser.getId()).isNotNull();
        Long teacherId = (Long) httpSession.getAttribute(UserService.USER_ID);
        Assertions.assertThat(teacherId).isNotNull();
        Assertions.assertThat(teacherId).isNotEqualTo(0L);
        Assertions.assertThat(teacherId).isEqualTo(testUser.getId());
    }

    @Test
    public void meTest() {
        logger.info("已登录，获取当前登录用户");
        User user = new User();
        user.setUsername("InAbxUsHyQ2dXKbWfueZR6WBfH7ZpLLx");
        user.setPassword("LdAUkm37Q5P0oEDLDJtJqKJMJwHaYTmq");
        userRepository.save(user);
        userService.login(user);

        logger.debug("得到登录的用户并断言");
        User loginUser = userService.me();
        Assertions.assertThat(loginUser.getId()).isNotNull();
        Assertions.assertThat(loginUser.getUsername()).isNotNull();
    }

    @Test
    public void logout() throws AuthException {
        logger.info("未登录，直接注销，断言异常");
        boolean catchException = false;
        try{
            userService.logout();
        }catch (AuthException e) {
            catchException = true;
        }
        Assertions.assertThat(catchException).isTrue();

        logger.info("已登录");
        User user = new User();
        user.setUsername("InAbxUsHyQ2dXKbWfueZR6WBfH7ZpLLx");
        user.setPassword("LdAUkm37Q5P0oEDLDJtJqKJMJwHaYTmq");
        userRepository.save(user);
        userService.login(user);

        logger.debug("正常获取");
        User currentLoginUser = userService.me();
        Assertions.assertThat(currentLoginUser);

        logger.debug("注销");
        userService.logout();
    }

}