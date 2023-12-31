package com.mysql.smart.service;

import com.mysql.smart.domain.User;
import com.mysql.smart.repository.UserDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User register(User user) {
        Optional<User> existingUser = userDao.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            return null; // 用户已存在
        } else {
            // 哈希用户密码  自动加盐
            user.setPassword(User.getPwdHash().hashPassword(user.getPassword()));
            //返回创建好的用户对象(带uid)
            User newUser = userDao.save(user);
            // 重要信息置空
            if (newUser != null) {
                newUser.setPassword("");
            }
            return newUser;
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUserName(username).orElse(null);
        if (user != null && User.getPwdHash().checkPassword(password, user.getPassword())) {
            // 重要信息置空
            user.setPassword("");
            return user;
        }
        return null;
    }

    /*@Override
    public User findByUsername(String username) {
        return userDao.findByUserName(username).orElse(null);
    }

    // 其他方法的实现
    // ...
    @Override
    public User updateSceneStatus(long id,String scene,int status){
        User user=userDao.findById(id).orElse(null);
        if(user!=null){
            user.updateUserSceneStatus(id,scene,status);
            userDao.save(user);
            user.setPassword("");
            return user;
        }
        return null;
    }

    @Override
    public User querySceneStatus(long id){
        User user=userDao.findById(id).orElse(null);
        if(user!=null){
            user.setPassword("");
            return user;
        }
        return null;
    }*/

}
