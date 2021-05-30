package com.xihua.hotpot.service;

import com.xihua.hotpot.mapper.UserMapper;
import com.xihua.hotpot.pojo.User;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wufang
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    public User getUserByOpenId(String openId){
        return userMapper.selectByOpenId(openId);
    }

    /**
     * 插入新用户
     * @param user
     * @return
     */
    public int insertUser(User user){
        return userMapper.insert(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public ResponseDo loginUser(Integer name, String password) {
        User user = userMapper.selectById(name);
        if (user == null) {
            return new ResponseDo("账户不存在");
        } else if (password.equals(user.getPassword())) {
            return new ResponseDo("登录成功");
        } else {
            return new ResponseDo( "账户密码错误");
        }
    }
}
