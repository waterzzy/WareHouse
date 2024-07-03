package com.msb.mapper;

import com.msb.pojo.Supply;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface SupplyMapper {


    //查询所有的供应商
    List<Supply> queryAllSupply();
}
