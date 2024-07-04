package com.msb.service.impl;

import com.msb.entity.User;
import com.msb.mapper.UserMapper;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByCode(String userCode) {

        return userMapper.findUserByCode(userCode) ;
    }
}
