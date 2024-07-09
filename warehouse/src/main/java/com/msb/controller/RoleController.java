package com.msb.controller;

import com.msb.dto.AssignAuthDto;
import com.msb.pojo.Result;
import com.msb.pojo.Role;
import com.msb.page.Page;
import com.msb.service.RoleService;
import com.msb.utils.CurrentUser;
import com.msb.utils.TokenUtils;
import com.msb.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 查询所有角色的url接口 role/role-list
    @RequestMapping("/role-list")
    public Result roleList(){
        List<Role> roleList = roleService.queryAllRole();

        return Result.ok(roleList);
    }

    // 分页条件查询角色
    @RequestMapping("/role-page-list")
    public Result rolePageList(Page page, Role role){

        page = roleService.queryRolePage(page,role);

        return Result.ok(page);
    }

    @Autowired
    private TokenUtils tokenUtils;
    // 添加角色
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createId = currentUser.getUserId();
        role.setCreateBy(createId);

        Result result = roleService.addRole(role);

        return result;
    }


    //导出数据
    @RequestMapping("/exportTable")
    public Result exportTable(Page page, Role role){
        page = roleService.queryRolePage(page,role);
        return Result.ok(page.getResultList());
    }


    //改变角色状态
    @RequestMapping("/role-state-update")
    public Result setRoleState(@RequestBody Role role){
        Result result = roleService.setRoleState(role);
        return result;
    }

    //删除角色
    @RequestMapping("/role-delete/{roleId}")
    public Result deleteRole(@PathVariable Integer roleId){
        Result result = roleService.deleteRole(roleId);
        return result;
    }

    //查询角色分配到所有权限菜单
    @RequestMapping("/role-auth")
    private Result roleAuth(Integer roleId){
        List<Integer> authIdList = roleService.queryRoleAuthIds(roleId);
        return Result.ok(authIdList);
    }

    //给角色分配菜单权限的url接口
    @RequestMapping("/auth-grant")
    public Result grantAuth(@RequestBody AssignAuthDto assignAuthDto){

        roleService.addRoleAuth(assignAuthDto);

        return Result.ok("角色分配成功");
    }

    //修改角色描述
    @RequestMapping("/role-update")
    public Result updateRole(@RequestBody Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        // 拿到当前登录用户的id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        role.setUpdateBy(updateBy);

        Result result = roleService.setRoleByRoleId(role);

        return result;
    }
}
