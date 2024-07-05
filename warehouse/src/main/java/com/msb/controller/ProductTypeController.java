package com.msb.controller;

import com.msb.pojo.ProductType;
import com.msb.pojo.Result;
import com.msb.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/5
 */
@RequestMapping("/productCategory")
@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;


    /**
     * 查询所有商品的分类树
     * @return
     */
    @RequestMapping("/product-category-tree")
    public Result productCategoryTree(){
        //执行Service层方法
        List<ProductType> productTypeList = productTypeService.allProductTypeTree();
        //响应数据
        return Result.ok(productTypeList);
    }


    /**
     * 校验分类编码是否已存在的url接口/productCategory/verify-type-code
     * 前端添加分类的 分类编码输入框输入分类编码，当光标进入分类名称输入框时，就向后台发请求去验证分类编码是否是唯一的
     */
    @RequestMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode){
        //执行业务
        Result result = productTypeService.queryTypeByCode(typeCode);
        //响应
        return result;
    }


    /**
     * 添加分类
     *
     * @param productType
     * @return
     */
    @RequestMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType){

        //执行Service层添加分类的方法
        Result result = productTypeService.saveProductType(productType);

        //响应结果
        return result;
    }


    /**
     * 修改分类   只能修改分类名称和分类描述     分类编码是唯一标识
     * @param productType   接收前端传来的json数据
     * @return
     */
    @RequestMapping("/type-update")
    public Result updateType(@RequestBody ProductType productType){
        //执行业务方法
        Result result = productTypeService.updateProductType(productType);
        //响应结果
        return result;
    }


    /**
     * 删除分类   根据分类前台传来的分类id   根据分类id删除分类及其所有子级分类
     * @param typeId
     * @return
     */
    @RequestMapping("/type-delete/{typeId}")
    public Result deleteType(@PathVariable Integer typeId){
        //执行业务
        Result result = productTypeService.removeProductType(typeId);
        //响应
        return result;
    }
}
