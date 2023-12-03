package com.mysql.smart.controller;

import com.mysql.smart.domain.User;
import com.mysql.smart.service.UserService;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
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

    @PostMapping("/updatescene")
    public Result<User> updatescene(@RequestBody Map<String,Object>fields){
        int id=(int)fields.get("id");
        String field = (String) fields.get("scene");
        int value = (int) fields.get("status");
        User updatedscene = userService.updateSceneStatus(id, field, value);
        if (updatedscene != null) {
            return Result.success(updatedscene, "更新成功！");
        } else {
            return Result.error("103", "更新失败！");
        }
    }

    @PostMapping("/queryscene")
    public Result<User> queryscene(@RequestBody Map<String,Object>fields){
        int id=(int)fields.get("id");
        User user1=userService.querySceneStatus(id);
        if(user1!=null){
            return Result.success(user1,"查询成功");
        } else{
            return Result.error("104", "查询失败！");
        }
    }

}
