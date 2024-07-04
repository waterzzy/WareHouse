package com.msb.service;

import com.msb.page.Page;
import com.msb.pojo.User;

public interface UserService {
    //根据用户名查找用户的业务方法
    public User findUserByCode(String userCode);

    //分页查询用户的业务方法
    public Page queryUserPage(Page page, User user);
}
