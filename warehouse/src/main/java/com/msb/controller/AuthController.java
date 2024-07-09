package com.msb.controller;

import com.msb.pojo.Auth;
import com.msb.pojo.Result;
import com.msb.service.AuthService;
import com.msb.utils.CurrentUser;
import com.msb.utils.TokenUtils;
import com.msb.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    // 查询所有权限以权限树的形式展现
    @RequestMapping("/auth-tree")
    public Result loadAllAuthTree(){
        List<Auth> allAuthTree = authService.allAuthTree();
        return Result.ok(allAuthTree);
    }

    @Autowired
    private TokenUtils tokenUtils;


    //添加权限
    @RequestMapping("/auth-add")
    public Result addAuth(@RequestBody Auth auth, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        auth.setCreateBy(createBy);

        Result result = authService.addAuth(auth);

        return result;
    }


    //添加之前判断添加的三个东西
    @RequestMapping("/name-check")
    public Result checkName(String authName){
        Auth auth = authService.queryAuthByName(authName);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok(auth);
    }
    @RequestMapping("/url-check")
    public Result checkUrl(String authUrl){
        Auth auth = authService.queryAuthByUrl(authUrl);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok(auth);
    }
    @RequestMapping("/code-check")
    public Result checkCode(String authCode){
        Auth auth = authService.queryAuthByCode(authCode);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok(auth);
    }

    //修改权限
    @RequestMapping("/auth-update")
    public Result updateAuth(@RequestBody Auth auth,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id -- 修改角色的用户id
        int updateBy = currentUser.getUserId();

        auth.setUpdateBy(updateBy);

        return authService.updateAuth(auth);
    }

    //启用权限
    @RequestMapping("/auth-enable/{authId}")
    public Result enableAuth(@PathVariable Integer authId){
        return authService.setAuthState1(authId);
    }

    //禁用权限
    @RequestMapping("/auth-disable/{authId}")
    public Result disableAuth(@PathVariable Integer authId){
        return authService.setAuthState2(authId);
    }

    //删除权限
    @RequestMapping("/auth-delete/{authId}")
    public Result deleteAuth(@PathVariable Integer authId){
        return authService.deleteAuthById(authId);
    }

}
