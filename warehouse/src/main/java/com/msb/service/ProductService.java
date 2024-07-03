package com.msb.service;

import com.msb.page.Page;
import com.msb.pojo.Product;
import com.msb.pojo.ProductType;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/2
 */

public interface ProductService {

    //查询所有商品信息   分页查询
    Page selectAllProductPage(Page page,Product product);



}
