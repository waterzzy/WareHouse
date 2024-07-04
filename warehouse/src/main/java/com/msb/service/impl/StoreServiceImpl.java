package com.msb.service.impl;

import com.msb.entity.Store;
import com.msb.mapper.StoreMapper;
import com.msb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    /**
     *  查询所有的仓库
     * @return
     */
    @Override
    public List<Store> getAllStore() {
        List<Store> stores = storeMapper.quarryAllStore();
        return stores;
    }
}
