package com.msb.service.impl;

import com.msb.dto.AssignRoleDto;
import com.msb.mapper.RoleMapper;
import com.msb.mapper.UserRoleMapper;
import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.pojo.User;
import com.msb.mapper.UserMapper;
import com.msb.pojo.UserRole;
import com.msb.service.UserService;
import com.msb.utils.DigestUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    // 根据用户账号查询用户
    @Override
    public User queryUserByCode(String userCode) {

        return userMapper.findUserByCode(userCode) ;
    }
   /* //分页查询用户的业务方法
    @Override
    public Page queryUserPage(Page page, User user) {

        //查询用户总行数
        int userCount = userMapper.selectUserCount(user);

        //分页查询用户
        List<User> userList = userMapper.selectUserPage(page, user);

        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(userCount);
        page.setResultList(userList);

        return page;
    }*/


    //分页查询用户的业务方法
    @Override
    public Page queryUserPage(Page page, User user) {
        if(user.getUserType() == null || user.getUserType() == ""){
            //查询用户总行数
            int userCount = userMapper.selectUserCount(user);

            //分页查询用户
            List<User> userList = userMapper.selectUserPage(page, user);

            //将查询到的总行数和当前页数据组装到Page对象
            page.setTotalNum(userCount);
            page.setResultList(userList);

            return page;
        }else {
            int userCount = userMapper.selectUserCount(user);
//分页查询用户
            List<User> userList = userMapper.selectUserPage2(page, user);

//将查询到的总行数和当前页数据组装到Page对象
            page.setTotalNum(userCount);
            page.setResultList(userList);

            return page;

        }


    }


    //添加用户业务实现
    @Override
    public Result addUser(User user) {

        // 判断账号是否唯一
        User u = userMapper.findUserByCode(user.getUserCode());
        if(u != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"账号已存在");
        }

        // 对密码进行加密处理
        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);


        int i = userMapper.insertUser(user);
        if(i > 0){
            return Result.ok("用户添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用户添加失败");
    }

    //启用禁用用户实现
    @Override
    public Result setUserState(User user) {
        int i = userMapper.setStateByUserId(user.getUserId(),user.getUserState());
        if(i > 0){
            return Result.ok("启用或禁用用户成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"启用或禁用用户失败");
    }


    //给用户分配角色的业务方法
    @Transactional // 事务处理
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        userRoleMapper.deleteUserRoleByUserId(assignRoleDto.getUserId());

        List<String> roleNameList = assignRoleDto.getRoleCheckList();
        for(String roleName : roleNameList){
            Integer roleId = roleMapper.selectRoleIdByRoleName(roleName);
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(assignRoleDto.getUserId());
            userRoleMapper.insertUserRole(userRole);
        }
    }

    // 删除用户
    @Override
    public Result deleteUserByIds(List<Integer> userIdList) {
        int i = userMapper.deleteByUserIds(userIdList);
        if(i > 0){
            return Result.ok("用户删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用户删除失败");
    }

    //修改用户昵称
    @Override
    public Result updateUserNameById(User user) {
        int i = userMapper.updateUserNameByUserId(user);
        if(i > 0){
            return Result.ok("用户昵称修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"用户昵称修改失败");
    }

    //根据用户id重置密码
    @Override
    public Result setPwdByUserId(Integer userId) {
        String password = DigestUtil.hmacSign("123456");
        int i = userMapper.setPwdByUserId(userId,password);
        if(i > 0){
            return Result.ok("密码重置成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"密码重置失败");
    }
}
