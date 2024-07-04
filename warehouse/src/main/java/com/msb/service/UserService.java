package com.msb.service;

import com.msb.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    //根据用户账号查询用户对象
     User queryUserByCode(String userCode);
}
