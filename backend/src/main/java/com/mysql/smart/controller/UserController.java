package com.mysql.smart.controller;

import com.mysql.smart.domain.User;
import com.mysql.smart.service.UserService;
import com.mysql.smart.util.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mysql.smart.domain.SceneStatus;
import com.mysql.smart.repository.SceneStatusDao;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private SceneStatusDao sceneStatusDao;
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            SceneStatus sleepStatus = new SceneStatus(user.getId(), "sleep", 0);
            SceneStatus wakeStatus = new SceneStatus(user.getId(), "wake", 0);
            SceneStatus leaveHomeStatus = new SceneStatus(user.getId(), "leavehome", 0);
            SceneStatus returnHomeStatus = new SceneStatus(user.getId(), "returnhome", 0);
            sceneStatusDao.save(sleepStatus);
            sceneStatusDao.save(wakeStatus);
            sceneStatusDao.save(leaveHomeStatus);
            sceneStatusDao.save(returnHomeStatus);
            return Result.success(registeredUser, "注册成功！");
        } else {
            return Result.error("101", "用户名已存在！");
        }
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUserName(), user.getPassword());
        if (loggedInUser != null) {
            return Result.success(loggedInUser, "登录成功！");
        } else {
            return Result.error("102", "账号或密码错误！");
        }
    }
}
