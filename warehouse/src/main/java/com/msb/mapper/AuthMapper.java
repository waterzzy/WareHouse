package com.msb.mapper;

import com.msb.pojo.Auth;

import java.util.List;

public interface AuthMapper {
    //根据账号查询用户id查询 用户权限下的菜单方法
    List<Auth> findAuthByUid(Integer userId);

    //查询所有权限菜单
    public List<Auth> selectAllAuth();

    //添加权限
    public int insertAuth(Auth auth);
}
