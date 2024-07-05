package com.msb.mapper;

import com.msb.pojo.User;

public interface UserMapper {
    //根据账号查询用户信息
    User findUserByCode(String userCode);
}
