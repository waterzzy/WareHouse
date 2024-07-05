package com.msb.mapper;

import com.msb.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    //查询所有商品信息  分页
    List<Product> selectAllProductPage(@Param("product") Product product);

    //查询所有的商品数据  前台导出数据表格对象
    List<Product> queryAllProductTableData(@Param("product") Product product);

    //添加商品
    int insertProduct(Product product);

    //修该商品
    int updateProductById(Product product);

    //删除商品  根据商品id
    int deleteProductById(Integer productId);

    //批量删除  商品id数组
    int deleteProductByIdArray(Integer[] productIds);

    //修改商品上下架的状态
    int updateStateById(Product product);
}