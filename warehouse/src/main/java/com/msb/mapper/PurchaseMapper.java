package com.msb.mapper;

import com.msb.entity.BuyList;
import com.msb.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PurchaseMapper {
    //  查询采购单总行数
    public int selectPurchaseCount(BuyList buyList);
    //  分页查询采购单
    public List<BuyList> selectPurchasePage(@Param("page") Page page,@Param("buylist") BuyList buyList);
    //  添加采购单
    public int addBuyList(BuyList buyList);
    //  根据ID删除采购单
    public int delPurchaseById(Integer buyId);
    //  更新采购单信息     根据Id
    public int updatePurchaseById(BuyList buyList);
    //  根据ID将采购单状态改为已入库
    public int updateIsInById(Integer buyId);
}
