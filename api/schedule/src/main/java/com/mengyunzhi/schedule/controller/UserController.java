package com.mengyunzhi.schedule.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.config.View;
import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import com.mengyunzhi.schedule.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liyiheng
 * @time: 2018-11-08
 * @description: 用户的控制器
 **/
@RestController
@RequestMapping("/User")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    /**
     * @Param: [user, httpServletResponse]
     * @return: void
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description:  登录
     */
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse httpServletResponse) {
        logger.info("用户登录");
        if (userService.login(user)) {
            logger.info("登录成功");
        } else {
            httpServletResponse.setStatus(401);
            logger.info("登录失败");
        }
    }

    /**
     * @Param: [httpServletResponse]
     * @return: com.mengyunzhi.schedule.entity.User 当前用户
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 得到当前用户
     */
    @GetMapping("/me")
    @JsonView(View.Student.class)
    public User me(HttpServletResponse httpServletResponse) {
        return userService.me();
    }

    @GetMapping("/getCurrentLoginUser")
    @JsonView(User.class)
    public User getCurrentLoginTeacher(HttpServletResponse httpServletResponse) {
        return this.me(httpServletResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse httpServletResponse) {
        try {
            userService.logout();
        } catch (AuthException e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
