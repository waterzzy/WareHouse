package com.msb.service.impl;

import com.msb.mapper.StoreMapper;
import com.msb.pojo.Store;
import com.msb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //查询所有的仓库
    @Override
    public List<Store> queryAllStores() {
        List<Store> storeList = storeMapper.queryAllStores();
        return storeList;
    }
}
