package com.msb.service.impl;

import com.msb.pojo.User;
import com.msb.mapper.UserMapper;
import com.msb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryUserByCode(String userCode) {

        return userMapper.findUserByCode(userCode) ;
    }
}
