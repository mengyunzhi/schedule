package com.mengyunzhi.schedule.repository;

import com.mengyunzhi.schedule.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: liyiheng
 * @time: 2018-11-08
 * @description: User的仓库
 **/
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * @Param: [username]   用户名
     * @return: com.mengyunzhi.schedule.entity.User user对象
     * @Author: liyiheng
     * @Date: 11/8/2018
     * @Description: 通过username得到用户的对象
     */
    User findByUsername(String username);
}
