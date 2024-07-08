package com.msb.service;

import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.pojo.Store;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface StoreService {

    //查询所有的仓库
    List<Store> queryAllStores();


    //分页查询
    Page queryStorePage(Page page, Store store);

    /**
     * 添加仓库
     * @param store
     * @return
     */
    Result saveStore(Store store);

    //根据仓库id删除仓库
    Integer deleteStoreById(Integer storeId);

    //更新
    Result updateStore(Store store);

    List<Store> queryAllStore();
}
