package com.mysql.smart.controller;

import com.mysql.smart.domain.User;
import com.mysql.smart.service.UserService;
import com.mysql.smart.util.JwtUtil;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mysql.smart.util.ErrorCode.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    class TokenInfo {
        public String token;
        TokenInfo(String token) {
            this.token = token;
        }
    }
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            return Result.success(registeredUser, "注册成功！");
        } else {
            return Result.error(ACCOUNT_ALREADY_EXIST);
        }
    }

    @PostMapping("/login")
    public Result<TokenInfo> login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUserName(), user.getPassword());
        if (loggedInUser != null) {
            String token = JwtUtil.createToken(loggedInUser);
            return Result.success(new TokenInfo(token), "登录成功！");
        } else {
            return Result.error(ACCOUNT_PWD_NOT_EXIST);
        }
    }
}
