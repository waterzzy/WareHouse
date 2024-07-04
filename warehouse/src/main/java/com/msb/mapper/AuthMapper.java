package com.msb.mapper;

import com.msb.entity.Auth;
import com.msb.entity.User;

import java.util.List;

public interface AuthMapper {
    //根据账号查询用户id查询 用户权限下的菜单方法
    List<Auth> findAuthByUid(Integer userId);
}
