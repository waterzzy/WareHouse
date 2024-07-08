package com.msb.service.impl;

import com.msb.mapper.ProductMapper;
import com.msb.pojo.Product;
import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class ProductServiceImpl implements ProductService {
    //注入mapper层对象
    @Resource
    private ProductMapper productMapper;



    /**
     * 分页查询所有商品信息
     * @param page
     * @param product
     * @return
     */
    @Override
    public Page selectAllProductPage(Page page, Product product) {


        //查询商品总行数
        int productCount = productMapper.selectProductCount(product);

        //分页查询商品
        List<Product> productList = productMapper.selectAllProductPage(page, product);

        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(productCount);
        page.setResultList(productList);

        return page;
    }


    /**
     * 查询所有的商品信息   前台数据表格数据导出
     *
     * @param product
     * @return
     */
    @Override
    public List<Product> queryAllProductTableData(Product product) {

        return productMapper.queryAllProductTableData(product);
    }



    /*
      将配置文件的file.access-path属性值注入给service的accessPath属性,
     * 其为上传的图片保存到数据库中的访问地址的目录路径/img/upload/;
     */
    @Value("${file.access-path}")
    private String accessPath;
    /**
     *  添加商品
     * @param product
     * @return
     */
    @Override
    public Result saveProduct(Product product) {
        //处理上传的图片的访问地址 -- /img/upload/图片名称
        product.setImgs(accessPath+product.getImgs());

        //添加商品  返回受影响的行数
        int row = productMapper.insertProduct(product);
        //判断受影响的行数
        if (row>0){
            return Result.ok("商品添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品添加失败！");
    }


    /**
     * 修改商品
     * @param product
     * @return
     */
    @Override
    public Result updateProduct(Product product) {
        /*
          处理商品上传的图片的访问地址:
          如果product对象的imgs属性值没有以/img/upload/开始,说明商品的图片
          被修改了即上传了新的图片,那么product对象的imgs属性值只是图片的名称,
          则给图片名称前拼接/img/upload构成商品新上传的图片的访问地址;
         */
        //前端传给后端的图片---product中的imgs--是图片的名称---后端存数据库时，前面还要加上/img/upload/
        if(!product.getImgs().startsWith(accessPath)){
            product.setImgs(accessPath+product.getImgs());
        }

        //根据商品---修改商品信息   返回受影响的行数
        int  row = productMapper.updateProductById(product);
        //判断受影响的行数   返回结果
        if (row>0){
            return Result.ok("商品信息修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品信息修改失败！");
    }

    /**
     * 删除商品
     */
    @Override
    public Result deleteProduct(Integer productId) {
        //根据商品id删除商品
        int row = productMapper.deleteProductById(productId);

        //判断受影响的行数，返回结果
        if (row>0){
            return Result.ok("商品删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品删除失败！");
    }


    /**
     * 批量删除商品
     * @param productIds
     * @return
     */
    @Override
    public Result deleteProductArray(Integer[] productIds) {

        //批量删除 商品   根据商品id数组
        int rows = productMapper.deleteProductByIdArray(productIds);
        //判断受影响的行数   返回结果
        if (rows == productIds.length){
            return Result.ok("批量删除商品成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"批量删除商品失败！");
    }

    /**
     * 修该商品的上下架状态
     * @param product    当前商品的productId, 和要修改的upDownState上下架状态
     * @return
     */
    @Override
    public Result updateProductState(Product product) {
        //根据商品id去更新上下架状态
        int row = productMapper.updateStateById(product);
        //判断受影响的行数
        if (row>0){
            return Result.ok("修改状态成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改状态失败！");
    }
}
