package com.msb.controller;

import com.msb.base.BaseController;
import com.msb.page.Page;
import com.msb.pojo.*;
import com.msb.result.ResultInfo;
import com.msb.service.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/2
 */

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProductTypeService productTypeService;

    /**
     *   查询所有的商品信息----分页
     *
     * @param page   接收前台传来的pageNum=当前页 pageSize=每页显示的条数
     * @param product    用来接收前台传来的--查询条件
     * @return
     */
    @RequestMapping("/product-page-list")
    public ResultInfo productPageList(Page page,Product product){

        //调用Service层查询所有商品信息的方法
        Page allProductPage = productService.selectAllProductPage(page, product);

        return success(allProductPage);
    }


    /**
     *查询所有仓库
     *
     * @return
     */
    @RequestMapping("/store-list")
    public ResultInfo storeList(){

        //调用storeService层的方法
        List<Store> storeList = storeService.queryAllStores();

        //返回resultInfo结果对象
        return success(storeList);
    }


    /**
     * 查询 商品  的分类   -----添加商品页面的 种类下拉框    +  商品分类的层级树
     * @return
     */
    @RequestMapping("/category-tree")
    public ResultInfo categoryTree(){

        //调用ProductTypeService 层的查商品 分类的 方法
        //带层级关系的-----分类集合  手机parentId=0,typeId=11 他下面有华为手机、苹果手机parentId=11
        List<ProductType> TypeTreeList = productTypeService.allProductTypeTree();

        return success(TypeTreeList);
    }



    /**
     * 查询所有品牌
     *
     * @return
     */
    @RequestMapping("/brand-list")
    public ResultInfo brandList(){

        //调用BrandService层的查询所有品牌的方法
        List<Brand> brandList = brandService.queryAllBrand();

        //响应结果
        return success(brandList);
    }


    /**
     * 查询所有的供应商
     * @return
     */
    @RequestMapping("/supply-list")
    public ResultInfo supplyList(){
        //调用supplyService层的查询所有供应商的方法
        List<Supply> supplyList = supplyService.queryAllSupply();

        //响应结果
        return success(supplyList);
    }


    /**
     * 查询所有产地
     * @return
     */
    @RequestMapping("/place-list")
    public ResultInfo placeList(){

        //调用placeService层的查询所有产地的方法
        List<Place> placeList = placeService.queryAllPlace();

        //响应结果
        return success(placeList);
    }


    /**
     * 查询所有单位
     * @return
     */
    @RequestMapping("/unit-list")
    public ResultInfo unitList(){

        //调用unitService层的查所有单位的方法
        List<Unit> unitList = unitService.queryAllUnit();

        //响应结果
        return success(unitList);
    }
}
