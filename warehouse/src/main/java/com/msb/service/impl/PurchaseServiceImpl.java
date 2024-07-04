package com.msb.service.impl;

import com.msb.entity.BuyList;
import com.msb.entity.Result;
import com.msb.mapper.PurchaseMapper;
import com.msb.page.Page;
import com.msb.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 分页查询采购单，
     *
     * @param page
     * @param buyList
     * @return
     */
    @Override
    public Page selectPurchasePage(Page page, BuyList buyList) {
        //  查询采购单总行数
        int count = purchaseMapper.selectPurchaseCount(buyList);
        //  分页查询采购单
        List<BuyList> buyLists = purchaseMapper.selectPurchasePage(page, buyList);
        //  将擦互相拿到的总行数和当前页数具组装Page对象
        page.setTotalNum(count);        //总行数
        page.setResultList(buyLists);       //当前页查询到数据的List集合
        return page;
    }

    /**
     * 添加采购单
     * @param buyList
     * @return
     */
    @Override
    public Result addPurchase(BuyList buyList) {
        //  添加采购单
        int i = purchaseMapper.addBuyList(buyList);
        if (i>0){   //  添加成功
            return Result.ok("采购单添加成功了~");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"采购单添加失败咯~");
    }

    /**
     * 根据buyId-采购单ID  删除采购单
     * @param buyId
     * @return
     */
    @Override
    public Result delPurchase(Integer buyId) {
        int i = purchaseMapper.delPurchaseById(buyId);
        if (i>0){   //  删除成功
            return Result.ok("采购单已移除");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"采购单删除失败");
    }

    /**
     * 更新采购信息       根据采购单ID
     * @param buyList
     * @return
     */
    @Override
    public Result updatePurchase(BuyList buyList) {
        int i = purchaseMapper.updatePurchaseById(buyList);
        if (i>0){   //  更新成功
            return Result.ok("采购单更新成功~");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"采购单修改失败~");
    }
}
