package com.msb.controller;

import com.msb.dto.AssignRoleDto;
import com.msb.pojo.Result;
import com.msb.pojo.Role;
import com.msb.pojo.User;
import com.msb.page.Page;
import com.msb.service.RoleService;
import com.msb.service.UserService;
import com.msb.utils.CurrentUser;
import com.msb.utils.TokenUtils;
import com.msb.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户的url接口/user/user-list
     *
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数User对象用于接收请求参数用户名userCode、用户类型userType、用户状态userState;
     *
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/user-list")
    public Result userListPage(Page page, User user){
        page = userService.queryUserPage(page,user);
        return Result.ok(page);
    }

    @Autowired
    private TokenUtils tokenUtils;

    // 添加用户的url接口/user/addUser
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        // 拿到当前登录的用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        user.setCreateBy(createBy);

        Result result = userService.addUser(user);

        return result;
    }

    // 启用或禁用用户的url接口 user/updateState
    @RequestMapping("/updateState")
    public Result updateState(@RequestBody User user){
        Result result = userService.setUserState(user);
        return result;
    }

    @Autowired
    private RoleService roleService;

    //查询用户亦分配的角色的url接口 user/user-role-list/{userId}
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId){
        List<Role> roleList = roleService.queryRoleByUserId(userId);
        return Result.ok(roleList);
    }

    // /user/assignRole -- 给用户分配角色
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto){
        userService.assignRole(assignRoleDto);
        return Result.ok("分配角色成功");
    }


    //根据用户id删除单个用户
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUserById(@PathVariable Integer userId){
        Result result = userService.deleteUserByIds(Arrays.asList(userId));
        return result;
    }
    // 根据用户ids批量删除用户的url接口
    @RequestMapping("/deleteUserList")
    public Result deleteUserByIds(@RequestBody List<Integer> userIdList){
        Result result =userService.deleteUserByIds(userIdList);
        return result;
    }

    // 修改用户
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
         CurrentUser currentUser = tokenUtils.getCurrentUser(token);
         int updateBy = currentUser.getUserId();
         user.setUpdateBy(updateBy);

         Result result = userService.updateUserNameById(user);
         return result;
    }

    // 重置密码的 接口  /user/updatePwd/{userId}
    @RequestMapping("/updatePwd/{userId}")
    public Result updatePwd(@PathVariable Integer userId){
        Result result = userService.setPwdByUserId(userId);
        return result;
    }
}
