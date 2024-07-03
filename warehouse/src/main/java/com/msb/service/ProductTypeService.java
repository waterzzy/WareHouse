package com.msb.service;

import com.msb.pojo.ProductType;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface ProductTypeService {


    //查询所有商品的  分类
    List<ProductType> allProductTypeTree();
}
