package com.msb.controller;

import com.msb.pojo.Auth;
import com.msb.pojo.Result;
import com.msb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@Autowired
    private TokenUtils tokenUtils;


    //添加权限
    @RequestMapping("/auth-add")
    public Result addAuth(@RequestBody Auth auth, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        auth.setCreateBy(createBy);

        Result result = authService.addRole(auth);

        return result;
    }*/


    //添加之前判断添加的三个东西
    /*@RequestMapping("/name-check/{authName}")
    public Result checkName(@PathVariable String authName){
        Auth auth = authService.queryAuthByName(authName);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok("通过");
    }
    @RequestMapping("/url-check/{authUrl}")
    public Result checkUrl(@PathVariable String authUrl){
        Auth auth = authService.queryAuthByUrl(authUrl);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok("通过");
    }
    @RequestMapping("/code-check/{authCode}")
    public Result checkCode(@PathVariable String authCode){
        Auth auth = authService.queryAuthByCode(authCode);
        if(auth != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"已存在");
        }
        return Result.ok("通过");
    }*/


}
