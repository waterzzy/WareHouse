package com.msb.service;

import com.msb.pojo.User;

public interface UserService {
    //根据用户账号查询用户对象
     User queryUserByCode(String userCode);
}
