package com.msb.service;

import com.msb.pojo.ProductType;
import com.msb.pojo.Result;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface ProductTypeService {


    //查询所有商品的  分类
    List<ProductType> allProductTypeTree();

    //校验分类编码是否已存在的业务方法
    Result queryTypeByCode(String typeCode);

    //添加商品分类的方法
    Result saveProductType(ProductType productType);

    //修改分类  根据分类id
    Result updateProductType(ProductType productType);

    //删除分类   根据分类id   根据分类id删除分类及其所有子级分类的方法
    Result removeProductType(Integer typeId);
}
