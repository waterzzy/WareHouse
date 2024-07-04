package com.msb.service.impl;

import com.msb.mapper.UserMapper;
import com.msb.page.Page;
import com.msb.pojo.User;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据用户名查找用户的业务方法
    @Override
    public User findUserByCode(String userCode){
        User user = null;
        return user;
    }

    //分页查询用户的业务方法
    @Override
    public Page queryUserPage(Page page, User user){
        //
        int userCount = userMapper.selectUserCount(user);
        //
        List<User> userList = userMapper.selectUserPage(page,user);

        //
        page.setTotalNum(userCount);
        page.setResultList(userList);

        return page;
    }
}
