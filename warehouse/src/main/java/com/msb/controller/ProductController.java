package com.msb.controller;

import com.msb.page.Page;
import com.msb.pojo.*;
import com.msb.service.*;
import com.msb.utils.CurrentUser;
import com.msb.utils.TokenUtils;
import com.msb.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author dh
 * @date 2024/7/2
 */

@RequestMapping("/product")
@RestController
public class ProductController {
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

    @Autowired
    private TokenUtils tokenUtils;   //注入tokenUtils对象


    /**
     *   查询所有的商品信息----分页
     *
     * @param page   接收前台传来的pageNum=当前页 pageSize=每页显示的条数
     * @param product    用来接收前台传来的--查询条件
     * @return
     */
    @RequestMapping("/product-page-list")
    public Result productPageList(Page page, Product product){

        //调用Service层查询所有商品信息的方法
        Page allProductPage = productService.selectAllProductPage(page, product);

        return Result.ok(allProductPage);
    }


    /**
     *查询所有仓库
     *
     * @return
     */
    @RequestMapping("/store-list")
    public Result storeList(){

        //调用storeService层的方法
        List<Store> storeList = storeService.queryAllStores();

        //返回resultInfo结果对象
        return Result.ok(storeList);
    }


    /**
     * 查询 商品  的分类   -----添加商品页面的 种类下拉框    +  商品分类的层级树
     * @return
     */
    @RequestMapping("/category-tree")
    public Result categoryTree(){

        //调用ProductTypeService 层的查商品 分类的 方法
        //带层级关系的-----分类集合  手机parentId=0,typeId=11 他下面有华为手机、苹果手机parentId=11
        List<ProductType> TypeTreeList = productTypeService.allProductTypeTree();

        //响应数据结果
        return Result.ok(TypeTreeList);
    }



    /**
     * 查询所有品牌
     *
     * @return
     */
    @RequestMapping("/brand-list")
    public Result brandList(){

        //调用BrandService层的查询所有品牌的方法
        List<Brand> brandList = brandService.queryAllBrand();

        //响应结果
        return Result.ok(brandList);
    }


    /**
     * 查询所有的供应商
     * @return
     */
    @RequestMapping("/supply-list")
    public Result supplyList(){
        //调用supplyService层的查询所有供应商的方法
        List<Supply> supplyList = supplyService.queryAllSupply();

        //响应结果
        return Result.ok(supplyList);
    }


    /**
     * 查询所有产地
     * @return
     */
    @RequestMapping("/place-list")
    public Result placeList(){

        //调用placeService层的查询所有产地的方法
        List<Place> placeList = placeService.queryAllPlace();

        //响应结果
        return Result.ok(placeList);
    }


    /**
     * 查询所有单位
     * @return
     */
    @RequestMapping("/unit-list")
    public Result unitList(){

        //调用unitService层的查所有单位的方法
        List<Unit> unitList = unitService.queryAllUnit();

        //响应结果
        return Result.ok(unitList);
    }


    /**
     * 查询所有的商品数据--- 前台导出所有商品数据---Excel
     *
     * @param product
     * @return
     */
    @RequestMapping("/exportTable")
    public Result exportTableData(Product product){

        //查询所有的商品   ----  含条件搜索
        List<Product> productList = productService.queryAllProductTableData(product);

        return Result.ok(productList);
    }




    /**
     * 将配置文件的file.upload-path属性值注入给控制器的uploadPath属性,
     * 其为图片上传到项目的目录路径(类路径classes即resources下的static/img/upload);
     */
    @Value("${file.upload-path}")
    private String uploadPath;

    /**
     * 上传图片的url接口/product/img-upload
     *
     * 参数MultipartFile file对象封装了上传的图片;  file 接收前台上传的图片
     *
     * @CrossOrigin表示该url接口允许跨域请求;
     */
    @CrossOrigin    //跨域支持
    @PostMapping("/img-upload")
    public Result uploadImg(MultipartFile file){

        try {
            //拿到图片上传到的目录(类路径classes下的static/img/upload)的File对象
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            //拿到图片上传到的目录的磁盘路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            //拿到图片保存到的磁盘路径
            String fileUploadPath = uploadDirPath + "\\" + file.getOriginalFilename();
            //保存图片
            file.transferTo(new File(fileUploadPath));
            //成功响应
            return Result.ok("图片上传成功！");
        } catch (IOException e) {
            //失败响应
            return Result.err(Result.CODE_ERR_BUSINESS,"图片上传失败！");
        }
    }


    /**
     * 添加商品
     * @param product   接收前台添加商品表单提交的json数据
     * @param token   将请求头Token的值即客户端归还的token赋值给参数变量token;
     * @return
     */
    @RequestMapping("/product-add")
    public Result addProduct(@RequestBody Product product,
                                 @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录用户对象的id===商品表中商品的create_by 也就是商品创建人、
        int createBy = currentUser.getUserId();
        //设置create_by商品创建人的id
        product.setCreateBy(createBy);

        //调用productService层的添加商品的方法进行添加
        Result result = productService.saveProduct(product);

        //响应结果数据
        return result;
    }


    /**
     *  修改商品信息
     *
     * @param product   修改后表单提交的数据
     * @param token      客户端归还的token令牌
     * @return
     */
    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product,
                                @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //获取当前登录用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //拿到当前登录用户的id
        int updateBy = currentUser.getUserId();
        product.setUpdateBy(updateBy);

        //执行修改商品的方法
        Result result = productService.updateProduct(product);

        //响应结果数据
        return result;
    }


    /**
     * 删除商品  删除单个商品   根据商品id
     *
     * @param productId
     * @return
     */
    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){

        //调用Service层的删除方法
        Result result = productService.deleteProduct(productId);

        //响应结果
        return result;
    }


    /**
     * 批量删除商品 ----  根据前台传来的商品id数组
     *
     * @param productIds
     * @return
     */
    @RequestMapping("/product-list-delete")
    public Result deleteProductList(@RequestBody Integer[] productIds){

        //执行Service层的  批量  删除商品的方法
        Result result = productService.deleteProductArray(productIds);

        return result;
    }


    /**
     * 修改商品的  上架 下架 状态
     * 前台传会传  当前商品的productId, 和要修改的upDownState 上下架状态过来
     * @return
     */
    @RequestMapping("/state-change")
    public Result changeProductState(@RequestBody Product product){

        //执行Service层的修改上下架的方法
        Result result = productService.updateProductState(product);

        //响应结果
        return  result;
    }

}
