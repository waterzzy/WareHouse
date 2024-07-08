package com.msb.service.impl;


import com.msb.pojo.OutStore;
import com.msb.pojo.Product;
import com.msb.pojo.Result;
import com.msb.mapper.OutStoreMapper;
import com.msb.mapper.ProductMapper;
import com.msb.page.Page;
import com.msb.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加出库单
     * @param outStore
     * @return
     */
    @Override
    public Result addOutStore(OutStore outStore) {
        int i = outStoreMapper.addOutStore(outStore);
        if (i>0){   //添加成功
            return Result.ok("出库单添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出库单添加失败");
    }

    /**
     * 分页查询出库单
     * @param page
     * @param outStore
     * @return
     */
    @Override
    public Page outStorePage(Page page, OutStore outStore) {
        //  查询出库单总数量
        int count = outStoreMapper.queryOutStoreCount(outStore);
        //  分页查询出库单
        List<OutStore> outStores = outStoreMapper.queryOutStorePage(page, outStore);
        page.setTotalNum(count);
        page.setResultList(outStores);
        return page;
    }

    /**
     * 出库单确认
     * @param outStore
     * @return
     */
    @Transactional  //事务处理
    @Override
    public Result outStoreConfirm(OutStore outStore) {
        //  根据商品ID查询该商品所有信息，并封装
        Product product = productMapper.queryProductById(outStore.getProductId());
        //  判断库存是否充足
        if (product.getProductInvent()  < outStore.getOutNum()){
            return Result.err(Result.CODE_ERR_BUSINESS,"商品库存不足");
        }
        int i = outStoreMapper.updateIsInById(outStore.getOutsId());
        if (i>0){   //  出库单状态更改成功
            int j = productMapper.addInventById(outStore.getProductId(), -outStore.getOutNum());
            if (j>0){
                return Result.ok("出库成功");
            }
            return Result.err(Result.CODE_ERR_BUSINESS,"出库失败~");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出库失败~");
    }

    /**
     *  导出数据
     * @param outStore
     * @return
     */
    @Override
    public List<OutStore> outStoreExportTable(OutStore outStore) {
        List<OutStore> outStores = outStoreMapper.exportTable(outStore);
        return outStores;
    }
}
