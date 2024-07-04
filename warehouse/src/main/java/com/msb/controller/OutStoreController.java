package com.msb.controller;

import com.msb.Utils.CurrentUser;
import com.msb.Utils.TokenUtils;
import com.msb.Utils.WarehouseConstants;
import com.msb.entity.OutStore;
import com.msb.entity.Result;
import com.msb.entity.Store;
import com.msb.page.Page;
import com.msb.service.OutStoreService;
import com.msb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/outstore")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils  tokenUtils;
    @Autowired
    private StoreService storeService;
    /**
     * 添加出库单
     *@RequestBody + OutStore将请求传递的json数据封装到OutStore对象中
     * @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token
     * 将请求头Token的值客户端归还的token赋值给参数变量token
     */
    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore,
                              @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //  从token中获取当前登录用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //  获取当前登录用户ID，即创建出库单人ID
        Integer userId = currentUser.getUserId();
        outStore.setCreateBy(userId);
        //  出库单添加
        Result result = outStoreService.addOutStore(outStore);
        return result;

    }
    /**
     *  查询出所有的仓库
     */
    @RequestMapping("/store-list")
    public Result queryAllStoreList(){
        List<Store> allStore = storeService.getAllStore();
        return Result.ok(allStore);
    }
    /**
     * 分页查询
     * 形参中的Page对象用于请求参数页码pageNum，每页行数pageSize
     * OutStore接收出库单参数
     */
    @RequestMapping("/outstore-page-list")
    public Result outStorePageList(Page page,OutStore outStore){
        return null;
    }

    /**
     * 确定出库
     * @return
     */
    @RequestMapping("/outstore-confirm")
    public Result outStoreConfirm(@RequestBody OutStore outStore){

        return null;
    }

}
