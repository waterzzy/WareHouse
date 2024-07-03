package com.msb.mapper;

import com.msb.pojo.ProductType;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface ProductTypeMapper {


    //查询所有商品  的分类
    List<ProductType> findAllProductType();
}
