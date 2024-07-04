package com.msb.mapper;

import com.msb.entity.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserMapper {
    //根据账号查询用户信息
    User findUserByCode(String userCode);
}
