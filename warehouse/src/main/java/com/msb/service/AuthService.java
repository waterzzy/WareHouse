package com.msb.service;

import com.msb.pojo.Auth;
import com.msb.pojo.Result;

import java.util.List;

public interface AuthService {
    //通过用户id查询 菜单树
    List<Auth> authTreeByUid(Integer userId);

    //查询所有权限菜单
    public List<Auth> allAuthTree();

    // 添加权限
    public Result addAuth(Auth auth);

    //判断
    public Auth queryAuthByName(String authName);
    public Auth queryAuthByUrl(String authName);
    public Auth queryAuthByCode(String authName);

    //修改权限名称和描述
    public Result updateAuth(Auth auth);

    //启用权限
    public Result setAuthState1(Integer authId);
    //禁用权限
    public Result setAuthState2(Integer authId);

    //删除权限
    public Result deleteAuthById(Integer authId);
}
