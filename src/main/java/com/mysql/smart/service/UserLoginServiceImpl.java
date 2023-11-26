package com.mysql.smart.service;

import com.mysql.smart.entity.User;
import com.mysql.smart.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userMapper;

    @Override
    public User register(User user) {
        // 加密用户密码  自动加盐
        user.setPassword(User.getPwdHash().hashPassword(user.getPassword()));
        return userMapper.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username).orElse(null);
        if (user != null && User.getPwdHash().checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username).orElse(null);
    }

    // 其他方法的实现
    // ...
}
