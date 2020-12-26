package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回 true 表示用户名已存在， 返回 false 表示用户名可用
     */
    boolean existsUsername(String username);

}
