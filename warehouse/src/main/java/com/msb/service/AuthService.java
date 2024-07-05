package com.msb.service;

import com.msb.pojo.Auth;

import java.util.List;

public interface AuthService {
    //通过用户id查询 菜单树
    List<Auth> authTreeByUid(Integer userId);
}
