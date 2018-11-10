package com.mengyunzhi.schedule.service;

import com.mengyunzhi.schedule.entity.User;
import javax.security.auth.message.AuthException;

/**
 * @author: liyiheng
 * @time: 2018-11-08
 * @description: user的service
 **/
public interface UserService {
    String USER_ID = "userId";
    /**
     * @Param: [user]
     * @return: boolean 登录是否成功
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description: 登录
     */
    boolean login(User user);

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description: 我是谁
     */
    User me();

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User 当前登录用户
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description:  获取当前登录用户
     */
    User getCurrentLoginUser() throws AuthException;

    /**
     * @Param: []
     * @return: void
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description:  注销登录
     */
    void logout() throws AuthException;

    /**
     * @Param: []
     * @return: boolean
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description: 是否认证
     */
    boolean isAuth();

    /**
     * @Param: []
     * @return: com.mengyunzhi.schedule.entity.User
     * @Author: liyiheng
     * @Date: 11/9/2018
     * @Description: 得到一个User
     */
    User getOneSavedUser();
}
