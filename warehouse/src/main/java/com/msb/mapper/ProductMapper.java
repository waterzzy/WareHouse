package com.msb.mapper;

import com.msb.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    //查询所有商品信息  分页
    List<Product> selectAllProductPage(@Param("product") Product product);

    //查询所有的商品数据  前台导出数据表格对象
    List<Product> queryAllProductTableData(@Param("product") Product product);
}