package com.msb.mapper;

import com.msb.page.Page;
import com.msb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //根据账号查询用户信息
    public User findUserByCode(String userCode);

    // 查询用户行数
    public Integer selectUserCount(User user);

    // 分页条件查询用户
    public List<User> selectUserPage(@Param("page") Page page, @Param("user") User user);

    // 添加用户
    public int insertUser(User user);

    // 根据用户id修改用户状态的方法
    public int setStateByUserId(Integer userId,String userState);

    //根据用户ids删除用户(x修改delete_id状态)
    public int deleteByUserIds(List<Integer> userIdList);

    //根据用户id修改用户昵称
    public int updateUserNameByUserId(User user);

    // 根据用户id重置密码
    public int setPwdByUserId(Integer userId,String password);
}
