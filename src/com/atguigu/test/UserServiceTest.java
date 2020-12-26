package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbji68", "666666", "bbji68@163.com"));
     }

     @Test
     public void login() {
         System.out.println(userService.login(new User(null, "wzg168", "123456", null)));
     }

     @Test
     public void existsUsername(){
        if(userService.existsUsername("bbji68")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用！");
        }
     }
}
