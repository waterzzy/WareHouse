package com.msb.mapper;

import com.msb.pojo.Brand;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface BrandMapper {

    //查询所有品牌
    List<Brand> queryAllBrand();
}
