package com.msb.mapper;

import com.msb.pojo.UserRole;

public interface UserRoleMapper {

    // 根据用户id删除用户已分配角色关系的方法
    public int deleteUserRoleByUserId(Integer userId);

    //添加用户角色关系的方法
    public int insertUserRole(UserRole userRole);

}
