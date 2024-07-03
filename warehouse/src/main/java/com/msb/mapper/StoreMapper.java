package com.msb.mapper;

import com.msb.pojo.Store;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface StoreMapper {

    //查询所有的仓库
    List<Store> queryAllStores();
}
