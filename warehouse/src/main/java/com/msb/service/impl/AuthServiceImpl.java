package com.msb.service.impl;

import com.alibaba.fastjson.JSON;
import com.msb.pojo.Auth;
import com.msb.mapper.AuthMapper;
import com.msb.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthMapper authMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 查询用户菜单树的业务方法
     * @param userId
     * @return
     */
    @Override
    public List<Auth> authTreeByUid(Integer userId) {
        //先从redis中查询缓存的用户菜单树
        String authTreeJson = redisTemplate.opsForValue().get("authTree" + userId);
        if (StringUtils.hasText(authTreeJson)){
            //将菜单树List<Auth>的json传话成List<Auth>并返回
            //将JSON转化成数组的形式 因为List的底层就是数组JSON.parseArray
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }
        //如果redis中没有菜单树的缓存
        List<Auth> allAuthList = authMapper.findAuthByUid(userId);
        //将所有的菜单List<Auth> 转成菜单树List(嵌套)
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        //向redis中缓存菜单树
        redisTemplate.opsForValue().set("authTree" + userId,JSON.toJSONString(authTreeList));
        return authTreeList ;
    }
    
    //将所有菜单List<Auth> 转成菜单树List<Auth>的递归
    public List<Auth> allAuthToAuthTree(List<Auth> allAuthList,Integer pid){
        //查询出所有子类级菜单
        List<Auth> firstLevelAuthList = new ArrayList<>();
        for (Auth auth: allAuthList
             ) {
            if (auth.getParentId().equals(pid)){
                firstLevelAuthList.add(auth);
            }
        }
        /*
        递归
         */
        //拿到每个菜单的子集菜单
        for (Auth firstAuth:firstLevelAuthList
             ) {
            List<Auth> secondLevelAuthList = allAuthToAuthTree(allAuthList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }
        return firstLevelAuthList;
    }

    //查询所有权限菜单
    // 查询方法上标注@Cacheable注释并指定缓存的键
    @Cacheable(key ="'all:authTree'")
    @Override
    public List<Auth> allAuthTree() {
        //查询所有权限菜单
        List<Auth> allAuthList = authMapper.selectAllAuth();
        // 将所有权限菜单List<Auth>转成权限菜单树List<Auth>
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList,0);
        return authTreeList;
    }
}
