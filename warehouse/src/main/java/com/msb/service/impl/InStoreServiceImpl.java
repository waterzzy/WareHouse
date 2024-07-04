package com.msb.service.impl;

import com.msb.entity.InStore;
import com.msb.entity.Result;
import com.msb.mapper.InStoreMapper;
import com.msb.mapper.PurchaseMapper;
import com.msb.page.Page;
import com.msb.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * //  添加入库单    采购人
     * @param inStore
     * @param buyId
     * @return
     */
    @Override
    public Result saveInStore(InStore inStore, Integer buyId) {
        //  添加入库单
        int i = inStoreMapper.addInStore(inStore);
        if (i>0){
            int j = purchaseMapper.updateIsInById(buyId);
            if (j>0){
                return Result.ok("入库单添加成功~");
            }
            return Result.err(Result.CODE_ERR_BUSINESS,"入库单添加失败");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"入库单添加失败");
    }

    @Override
    public Page selectInStorePage(Page page, InStore inStore) {
        //  分页查询入库单
        List<InStore> inStores = inStoreMapper.selectInStorePage(page, inStore);
        //  查询入库单总行数
        int count = inStoreMapper.selectInStoreCount(inStore);
        //  将入库单相关数据组装成Page对象
        page.setTotalNum(count);    //  总行数
        page.setResultList(inStores);   //  当前页查询到的数据的List集合
        return page;
    }
}
