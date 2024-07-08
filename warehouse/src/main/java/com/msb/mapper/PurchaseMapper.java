package com.msb.mapper;

import com.msb.pojo.BuyList;
import com.msb.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseMapper {
    //  查询采购单总行数
    public int selectPurchaseCount(BuyList buyList);

    //  分页查询采购单
    public List<BuyList> selectPurchasePage(@Param("page") Page page, @Param("buyList") BuyList buyList);

    //  添加采购单
    public int addBuyList(BuyList buyList);

    //  根据ID删除采购单
    public int delPurchaseById(Integer buyId);

    //  更新采购单信息  采购数量    根据Id
    public int updatePurchaseById(BuyList buyList);

    //  根据ID将采购单状态改为已入库
    public int updateIsInById(Integer buyId);

    //  导出数据表格
    public List<BuyList> exportTable(@Param("buyList") BuyList buyList);
}
