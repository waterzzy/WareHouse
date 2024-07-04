package com.msb.service;

import com.msb.entity.BuyList;
import com.msb.entity.Result;
import com.msb.page.Page;

public interface PurchaseService {
    //  分页查询采购单
    public Page selectPurchasePage(Page page, BuyList buyList);

    //  添加采购单
    public Result addPurchase(BuyList buyList);

    //  删除采购单
    public Result delPurchase(Integer buyId);

    //  修改采购单
    public Result updatePurchase(BuyList buyList);
}
