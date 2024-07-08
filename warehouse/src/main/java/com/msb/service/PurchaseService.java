package com.msb.service;

import com.msb.pojo.BuyList;
import com.msb.pojo.Result;
import com.msb.page.Page;

import java.util.List;

public interface PurchaseService {
    //  分页查询采购单
    public Page selectPurchasePage(Page page, BuyList buyList);

    //  添加采购单
    public Result addPurchase(BuyList buyList);

    //  删除采购单
    public Result delPurchase(Integer buyId);

    //  修改采购单
    public Result updatePurchase(BuyList buyList);

    //  导出数据表单
    public List<BuyList> exportTable(BuyList buyList);
}
