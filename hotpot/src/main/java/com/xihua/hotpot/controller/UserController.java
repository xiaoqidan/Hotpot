package com.xihua.hotpot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.pojo.User;
import com.xihua.hotpot.service.UserService;
import com.xihua.hotpot.util.HttpUrlConnectionUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param code
     * @param httpSession
     * @return
     */
    @GetMapping
    public User getUserInfo(@Param(value = "code") String code, HttpSession httpSession){
        // 小程序官方提供的接口   code是小程序前端传过来的值；
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxa5242ad82a67b057&secret=46c2dcc0636b32ebd462d8eb2771f92c&grent_type=authorization_code&js_code="+code;
        // 把code 给小程序官方传过去，获得一个OpenId 这个值就是用户的唯一标识
        String result = HttpUrlConnectionUtil.doGet(url);
        // 返回的是jsonz字符串，我们要解析出来
        JSONObject jsonObject = JSON.parseObject(result);
        String openId = (String) jsonObject.get("openid");
        System.out.println(openId);
        // 通过这个openId 从数据库查数据
        User user = userService.getUserByOpenId(openId);
        // 如果查不到，说明没用登录过，用户是第一次使用，那么就需要把openId插入到数据库
        if(user == null){
            User userNew = new User();
            userNew.setOpenid(openId);
            userNew.setAuthority(false);
            //插入数据库
            userService.insertUser(userNew);
            user = userNew;
        }
        httpSession.setAttribute("userId",String.valueOf(user.getId()));
        return user;
    }

    /**
     * 更新用户信息
     * @param user
     */
    @PostMapping
    public void postUserInfo(@RequestBody User user){
        userService.updateUser(user);
    }
}
