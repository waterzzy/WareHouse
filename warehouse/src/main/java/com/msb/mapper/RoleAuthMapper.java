package com.msb.mapper;

import com.msb.pojo.RoleAuth;

import java.util.List;

public interface RoleAuthMapper {

    // 根据角色id删除角色权限关系
    public int deleteRoleAuthByRoleId(Integer roleId);

    //根据角色id分配所有的权限菜单的id
    public List<Integer> selectAuthIdsByRoleId(Integer roleId);

    // t添加角色权限关系的方法
    public int insertRoleAuth(RoleAuth roleAuth);
}
