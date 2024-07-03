package com.msb.service.impl;

import com.msb.mapper.ProductTypeMapper;
import com.msb.pojo.ProductType;
import com.msb.service.ProductTypeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> allProductTypeTree() {

        //查询所有商品  分类
        List<ProductType> allTypeList =  productTypeMapper.findAllProductType();
        //将所有的 商品分类  转换成  商品分类树
        List<ProductType> typeTreeList = allTypeToTypeTree(allTypeList, 0);

        //返回商品分类树----前端树形结构
        return typeTreeList;
    }

    //将查询到的所有商品分类List<ProductType>转成商品分类树List<ProductType>的算法
    private List<ProductType> allTypeToTypeTree(List<ProductType> allTypeList, Integer parentId){

        //得到所有  分类的  父级类--集合
        List<ProductType> typeList = new ArrayList<>();
        for (ProductType productType : allTypeList) {
            if(productType.getParentId().equals(parentId)){  //parentId=0--父级
                typeList.add(productType);
            }
        }

        //递归得到所有  分类的   子级类--集合
        for (ProductType productType : typeList) {
            List<ProductType> childTypeList = allTypeToTypeTree(allTypeList, productType.getTypeId());
            productType.setChildProductCategory(childTypeList);
        }

        return typeList;
    }

}
