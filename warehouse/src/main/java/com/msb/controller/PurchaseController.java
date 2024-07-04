package com.msb.controller;


import com.msb.Utils.CurrentUser;
import com.msb.Utils.TokenUtils;
import com.msb.Utils.WarehouseConstants;
import com.msb.entity.BuyList;
import com.msb.entity.InStore;
import com.msb.entity.Result;
import com.msb.entity.Store;
import com.msb.mapper.PurchaseMapper;
import com.msb.page.Page;
import com.msb.service.InStoreService;
import com.msb.service.PurchaseService;
import com.msb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;

    /**
     * 添加采购单
     * post请求
     */
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody BuyList buyList) {
        // 执行采购单业务
        Result result = purchaseService.addPurchase(buyList);
        // 返回响应
        return result;
    }

    /**
     * 更新采购单
     */
    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody BuyList buyList) {
        Result result = purchaseService.updatePurchase(buyList);
        return result;
    }

    /**
     * 查询所有仓库
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> allStore = storeService.getAllStore();
        return Result.ok(allStore);
    }

    /**
     * 分页查询采购单
     * 参数Page对象用于接收请求参数页码pageNum\每页行数 pageSize
     * BuyList对象用于接收请求参数仓库id storeId,商品名称 productName\
     * 采购人buyUser\是否生成入库单isIn\起止时间startTime和endTime
     * get请求
     * 返回值result对象向客户端相应组装了所有的分页信息的Page对象
     */
    @RequestMapping("/purchase-page-list")
    public Result purchasePageList(Page page, BuyList buyList) {
        page = purchaseService.selectPurchasePage(page, buyList);
        return Result.ok(page);
    }

    /**
     * 删除采购单  根据ID
     *
     * @param buyId
     */
    @RequestMapping("/purchase-delete/{buyId}")
    public Result delPurchase(@PathVariable Integer buyId) {
        Result result = purchaseService.delPurchase(buyId);
        return result;
    }

    /**
     * 创建入库单
     *
     * @RequestBody BuyList buyList将请求传递的json数据封装到参数BuyList对象
     * @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token
     * 将请求头Token的值即客户端归还的token赋值给参数变量token
     */
    @RequestMapping("/in-warehouse-record-add")
    public Result createPurchase(@RequestBody BuyList buyList, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前登录用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        // 获取当前用户登录ID  也是创建入库单的用户ID
        Integer userId = currentUser.getUserId();
        // 创建入库对象，添加入库单信息
        InStore inStore = new InStore();
        inStore.setStoreId(buyList.getStoreId());
        inStore.setProductId(buyList.getProductId());
        inStore.setInNum(buyList.getFactBuyNum());
        inStore.setCreateBy(userId);
        // 创建入库单
        Result result = inStoreService.saveInStore(inStore, buyList.getBuyId());
        return result;
    }

}
