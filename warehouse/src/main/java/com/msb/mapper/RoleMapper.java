package com.msb.mapper;

import com.msb.pojo.Role;
import com.msb.page.Page;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface RoleMapper {

    //查询所有角色的方法
    public List<Role> selectAllRole();

    //根据用户id查询用户分配的所有角色
    public List<Role> selectRoleByUserId(Integer userId);

    // 根据角色名查询角色id的方法
    public Integer selectRoleIdByRoleName(String roleName);


    // 分页查询角色行数
    public Integer selectRoleRowCount(Role role);

    // 根据角色名称、角色代码、角色状态查询角色并分页
    public List<Role> selectRolePage(@Param("page") Page page,@Param("role") Role role);

    //根据角色名称或角色代码查询角色的方法
    public Role selectRoleByNameOrCode(String roleName,String roleCode);

    // 添加角色
    public Integer insertRole(Role role);

    // 根据角色id启用和禁用角色
    public Integer setStateByRoleId(Integer roleId,String roleState);

    // 删除角色
    public Integer deleteByRoleId(Integer roleId);

    //根据角色id修改角色描述的方法
    public int setDescByRoleId(Role role);
}
