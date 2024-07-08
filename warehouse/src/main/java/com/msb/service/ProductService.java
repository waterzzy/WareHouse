package com.msb.service;

import com.msb.page.Page;
import com.msb.pojo.Product;
import com.msb.pojo.Result;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/2
 */

public interface ProductService {

    //查询所有商品信息   分页查询
    Page selectAllProductPage(Page page, Product product);


    //查询所有的商品    前台导出表格数据
    List<Product> queryAllProductTableData(Product product);

    //添加商品
    Result saveProduct(Product product);

    //修改商品
    Result updateProduct(Product product);

    //删除商品
    Result deleteProduct(Integer productId);

    //批量删除商品
    Result deleteProductArray(Integer[] productIds);

    //修改商品的上下架状态
    Result updateProductState(Product product);
}
