package com.msb.mapper;

import com.msb.pojo.Unit;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface UnitMapper {

    //查询所有的单位
    List<Unit> queryAllUnit();
}
