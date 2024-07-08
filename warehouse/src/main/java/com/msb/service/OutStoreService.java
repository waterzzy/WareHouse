package com.msb.service;

import com.msb.pojo.OutStore;
import com.msb.pojo.Result;
import com.msb.page.Page;

import java.util.List;

public interface OutStoreService {
    //  添加出库单
    public Result addOutStore(OutStore outStore);

    //  分页查询出库单
    public Page outStorePage(Page page, OutStore outStore);
    //  出库单确定
    public Result outStoreConfirm(OutStore outStore);

    public List<OutStore> outStoreExportTable(OutStore outStore);

}
