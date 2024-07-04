package com.msb.service;

import com.msb.entity.OutStore;
import com.msb.entity.Result;
import com.msb.page.Page;

public interface OutStoreService {
    //  添加出库单
    public Result addOutStore(OutStore outStore);

    //  分页查询出库单
    public Page outStorePage(Page page, OutStore outStore);
    //  出库单确定
    public Result outStoreConfirm(OutStore outStore);

}
