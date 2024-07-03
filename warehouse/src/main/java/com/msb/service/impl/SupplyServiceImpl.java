package com.msb.service.impl;

import com.msb.mapper.SupplyMapper;
import com.msb.pojo.Supply;
import com.msb.service.SupplyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class SupplyServiceImpl implements SupplyService {

    @Resource
    private SupplyMapper supplyMapper;

    //查询所有的供应商
    @Override
    public List<Supply> queryAllSupply() {

        return supplyMapper.queryAllSupply();
    }
}