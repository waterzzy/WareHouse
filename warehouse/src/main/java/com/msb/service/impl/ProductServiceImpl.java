package com.msb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb.mapper.ProductMapper;
import com.msb.page.Page;
import com.msb.pojo.Product;
import com.msb.service.ProductService;
import jakarta.annotation.Resource;
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
    public Page selectAllProductPage(Page page,Product product) {

        //开启分页
        PageHelper.startPage(page.getPageNum(),page.getPageSize());

        //得到对应的分页对象
        PageInfo<Product>pageInfo=new PageInfo<>(productMapper.selectAllProductPage(product));

        //查到的总记录数
        page.setTotalNum((int) pageInfo.getTotal());
        //查到的商品list集合数据
        page.setResultList(pageInfo.getList());

        //返回page对象
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
}
