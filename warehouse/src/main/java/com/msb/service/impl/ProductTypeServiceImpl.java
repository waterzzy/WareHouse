package com.msb.service.impl;

import com.msb.mapper.ProductTypeMapper;
import com.msb.pojo.ProductType;
import com.msb.pojo.Result;
import com.msb.service.ProductTypeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    /**
     * 查询所有商品的分类-----树形结构
     * @return
     */
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


    //校验分类编码是否已存在的业务方法
    @Override
    public Result queryTypeByCode(String typeCode) {

        //根据分类编码查询商品分类
        ProductType productType = productTypeMapper.findTypeByCode(typeCode);

        //根据前台输入的分类编码查到结果为null====表示该分类编码唯一===可添加

        return Result.ok(productType==null);
    }


    /**
     * 添加商品分类
     *
     *    @CacheEvict(key = "'all:typeTree'")清除所有商品分类树的缓存;
     * @param productType
     * @return
     */
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result saveProductType(ProductType productType) {

        int row = productTypeMapper.insertProductType(productType);
        if (row>0){
            return Result.ok("添加分类成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加分类失败！");
    }


    /**
     * 修改分类
     * @param productType
     * @return
     */
    @Override
    public Result updateProductType(ProductType productType) {
        //判断受影响的行数   返回数据结果
        int row = productTypeMapper.updateTypeById(productType);
        if (row>0){
            return Result.ok("修改分类成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改分类失败！");
    }


    /**
     * 删除分类    根据分类id
     *
     * @CacheEvict(key = "'all:typeTree'")清除所有商品分类树的缓存;
     * @param typeId
     * @return
     */
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result removeProductType(Integer typeId) {

        //根据分类id删除分类及其所有子级分类
        int row = productTypeMapper.deleteTypeById(typeId);
        if (row>0){
            return Result.ok("分类删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"分类删除失败！");
    }
}
