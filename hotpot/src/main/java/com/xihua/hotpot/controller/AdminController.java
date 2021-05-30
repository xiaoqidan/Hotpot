package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员登录
 * @date 2021/5/9 10:27
 */
@RequestMapping(Const.hg + "/admin")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseDo loginUser(@RequestParam(value = "name") Integer name, @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }
}
