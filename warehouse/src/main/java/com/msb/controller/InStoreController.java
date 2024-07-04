package com.msb.controller;

import com.msb.entity.InStore;
import com.msb.entity.Result;
import com.msb.entity.Store;
import com.msb.page.Page;
import com.msb.service.InStoreService;
import com.msb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instore")
public class InStoreController {
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;

    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> allStore = storeService.getAllStore();
        return Result.ok(allStore);
    }

    /**
     * 分页查询入库单
     * 参数Page对象用于接受请求参数页码pageNum,每页行数pageSize
     * 参数InStore对象用于接收请购参数仓库id storeId 商品名称 productName 起止时间 startTime 和 endTime
     * <p>
     * 返回值 Result 对象向前端返回组组装所有分页信息的Page对象
     */
    @RequestMapping("/instore-page-list")
    public Result inStorePageList(Page page, InStore inStore) {
        page = inStoreService.selectInStorePage(page, inStore);
        return Result.ok(page);
    }
    /**
     * 确定入库
     * @RequestBody + InStore将put请求传递的Json数据封装到参数InStore对象中
     */
    @RequestMapping("/instore-confirm")
    public Result inStoreConfirm(@RequestBody InStore inStore){
        Result result = inStoreService.inStoreConfirm(inStore);
        return result;
    }
}
