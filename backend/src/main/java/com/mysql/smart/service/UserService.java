package com.mysql.smart.service;

import com.mysql.smart.domain.User;

public interface UserService {

    // 用户注册方法
    User register(User user);

    // 用户登录方法
    User login(String username, String password);

    // 根据用户名查找用户
    User findByUsername(String username);

    // 其他可能的业务方法
    // ...
    User updateSceneStatus(long id,String scene,int status);

    User querySceneStatus(long id);
}
