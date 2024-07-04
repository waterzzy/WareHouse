package com.msb.controller;

import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.pojo.User;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user-list")
    public Result userListPage(Page page, User user){
        //
        page = userService.queryUserPage(page,user);

        return Result.ok(page);
    }
}
