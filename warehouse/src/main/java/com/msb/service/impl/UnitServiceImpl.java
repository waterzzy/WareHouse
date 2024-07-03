package com.msb.service.impl;

import com.msb.mapper.UnitMapper;
import com.msb.pojo.Unit;
import com.msb.service.UnitService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class UnitServiceImpl implements UnitService {
    @Resource
    private UnitMapper unitMapper;

    //查询所有的 单位
    @Override
    public List<Unit> queryAllUnit() {

        return unitMapper.queryAllUnit();
    }
}
