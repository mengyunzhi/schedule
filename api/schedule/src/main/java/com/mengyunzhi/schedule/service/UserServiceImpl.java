package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.User;
import com.mengyunzhi.schedule.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpSession;

@Service
/**
 * @author: liyiheng
 * @time: 2018-11-08
 * @description:
 **/
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    UserRepository userRepository;
    @Autowired
    HttpSession httpSession;

    /**
     * @Param: [user]
     * @return: boolean  登录是否成功
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description: 登录
     */
    @Override
    public boolean login(User user) {
        logger.debug("获取相关用户");
        User persistUser = userRepository.findByUsername(user.getUsername());
        if (persistUser == null) {
            return false;
        }

        if (!persistUser.getPassword().equals(user.getPassword())) {
            return false;
        }

        logger.debug("记录当前用户的id");
        httpSession.setAttribute(UserService.USER_ID, persistUser.getId());

        logger.debug("写入userId");
        user.setId(persistUser.getId());

        return true;
    }

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 判断我是谁
     */
    @Override
    public User me() {
        Long userId = (Long) httpSession.getAttribute(UserService.USER_ID);
   //     if (userId == null)
     //       return null;
        User user = userRepository.findOne(userId);
        return user;
    }

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 当前登录用户
     */
    @Override
    public User getCurrentLoginUser() throws AuthException {
        return this.me();
    }

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 注销
     */
    @Override
    public void logout() throws AuthException {

        Long userId = (Long) httpSession.getAttribute(UserService.USER_ID);
        if (userId == null) {
            throw new AuthException("please login first");
        }

        httpSession.removeAttribute(UserService.USER_ID);
    }

    /**
     * @Param: []
     * @return: boolean
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 是否登录
     */
    @Override
    public boolean isAuth() {
        Long userId = (Long)httpSession.getAttribute(UserService.USER_ID);
        return userId != null;
    }

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 得到一个User
     */
    @Override
    public User getOneSavedUser() {
        User user = new User();
        user.setUsername("InAbxUsHyQ2dXKbWfueZR6WBfH7ZpLLx");
        user.setPassword("cFHXuqByyHb600Xz9lmlrnoE7mHETm7f");
        userRepository.save(user);
        return user;
    }

}
