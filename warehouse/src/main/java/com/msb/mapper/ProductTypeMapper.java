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

    //根据分类编码查询商品分类的方法
    ProductType findTypeByCode(String typeCode);

    //添加商品分类
    int insertProductType(ProductType productType);

    //修改分类  根据分类id
    int updateTypeById(ProductType productType);

    //根据分类id删除分类及其所有子级分类的方法
    int deleteTypeById(Integer typeId);
}
