package com.msb.service.impl;

import com.msb.dto.AssignAuthDto;
import com.msb.pojo.Result;
import com.msb.pojo.Role;
import com.msb.pojo.RoleAuth;
import com.msb.mapper.RoleAuthMapper;
import com.msb.mapper.RoleMapper;
import com.msb.page.Page;
import com.msb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 只定缓存的名称（数据保存到redis中的键的前缀，一般值给标注@CacheConfig注解的类的全路径）
@CacheConfig(cacheNames = "com.msb.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 查询所有角色的业务实现
    @Cacheable(key = "'all:role'")
    @Override
    public List<Role> queryAllRole() {
        return roleMapper.selectAllRole();
    }
    // 根据用户id查询改用户有少个角色的业务方法
    @Override
    public List<Role> queryRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    // 分页查询角色的
    @Override
    public Page queryRolePage(Page page, Role role) {
        //查询角色行数
        Integer count = roleMapper.selectRoleRowCount(role);

        //分页查询角色
        List<Role> roleList = roleMapper.selectRolePage(page,role);

        // 组装分页信息 （主要逻辑是这步）
        page.setTotalNum(count);
        page.setResultList(roleList);

        return page;
    }

    // 添加角色
    @CacheEvict(key = "'all:role'")
    @Override
    public Result addRole(Role role) {
        Role r = roleMapper.selectRoleByNameOrCode(role.getRoleName(),role.getRoleCode());
        if(r != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"角色已存在");
        }
        int i = roleMapper.insertRole(role);
        if(i > 0){
            return Result.ok("添加角色成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加角色失败");
    }

    //改变角色状态
    @CacheEvict(key = "'all:role'")
    @Override
    public Result setRoleState(Role role) {
        int i = roleMapper.setStateByRoleId(role.getRoleId(),role.getRoleState());
        if(i> 0){
            return Result.ok("启用或禁用角色成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"启用或禁用角色失败");
    }

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    //删除角色
    @CacheEvict(key = "'all:role'")
    @Override
    public Result deleteRole(Integer roleId) {
        int i = roleMapper.deleteByRoleId(roleId);
        if(i > 0){
            //删除角色权限关系
            roleAuthMapper.deleteRoleAuthByRoleId(roleId);

            return Result.ok("角色删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色删除失败");
    }


    //查询角色分配的所有权限的菜单
    @Override
    public List<Integer> queryRoleAuthIds(Integer roleId) {

        return roleAuthMapper.selectAuthIdsByRoleId(roleId);
    }

    //给角色分配权限的业务方法
    @Transactional // 事务处理
    @Override
    public void addRoleAuth(AssignAuthDto assignAuthDto) {
        //删除角色之前分配的所有权限
        roleAuthMapper.deleteRoleAuthByRoleId(assignAuthDto.getRoleId());
        //添加角色分配的权限关系
        List<Integer> authIdList = assignAuthDto.getAuthIds();
        for (Integer authId: authIdList) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(assignAuthDto.getRoleId());
            roleAuth.setAuthId(authId);
            roleAuthMapper.insertRoleAuth(roleAuth);
        }

    }


    //修改角色描述
    @CacheEvict(key = "'all:role'")
    @Override
    public Result setRoleByRoleId(Role role) {
        int i = roleMapper.setDescByRoleId(role);
        if(i > 0){
            return Result.ok("修改角色描述成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色修改失败");
    }
}
