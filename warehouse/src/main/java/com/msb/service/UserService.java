package com.msb.service;

import com.msb.dto.AssignRoleDto;
import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.pojo.User;

import java.util.List;

public interface UserService {
    //根据用户账号查询用户对象
    User queryUserByCode(String userCode);

    //分页查询用户的业务方法
    public Page queryUserPage(Page page, User user);

    //添加用户的业务方法
    public Result addUser(User user);

    //启用或禁用用户的业务方法
    public Result setUserState(User user);

    //给用户分配角色的业务方法
    public void assignRole(AssignRoleDto assignRoleDto);

    // 删除用户
    public Result deleteUserByIds(List<Integer> userIdList);

    //修改用户昵称
    public Result updateUserNameById(User user);

    //根据用户id重置密码
    public Result setPwdByUserId(Integer userId);
}
