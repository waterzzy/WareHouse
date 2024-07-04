package com.msb.service.impl;


import com.msb.entity.OutStore;
import com.msb.entity.Result;
import com.msb.mapper.OutStoreMapper;
import com.msb.page.Page;
import com.msb.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreMapper outStoreMapper;

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

        int count = outStoreMapper.queryOutStoreCount(outStore);

        return null;
    }

    @Override
    public Result outStoreConfirm(OutStore outStore) {
        return null;
    }
}
