package com.msb.service;

import com.msb.entity.BuyList;
import com.msb.entity.Result;
import com.msb.page.Page;

public interface PurchaseService {
    public Page selectPurchasePage(Page page, BuyList buyList);

    public Result addPurchase(BuyList buyList);

    public Result delPurchase(Integer buyId);

    public Result updatePurchase(BuyList buyList);
}
