package com.msb.service;

import com.msb.dto.AssignAuthDto;
import com.msb.pojo.Result;
import com.msb.pojo.Role;
import com.msb.page.Page;

import java.util.List;

public interface RoleService {

    // 查询所有角色的业务方法
    public List<Role> queryAllRole();

    // 根据用户id查询改用户有少个角色的业务方法
    public List<Role> queryRoleByUserId(Integer userId);

    // 分页查询角色的
    public Page queryRolePage(Page page, Role role);

    //添加角色
    public Result addRole(Role role);

    //改变角色状态
    public Result setRoleState(Role role);

    //删除角色
    public Result deleteRole(Integer roleId);


    //查询角色分配的所有权限的菜单的id
    public List<Integer> queryRoleAuthIds(Integer roleId);

    //给角色分配权限(添加)
    public void addRoleAuth(AssignAuthDto assignAuthDto);

    //修改角色描述
    public Result setRoleByRoleId(Role role);
}
