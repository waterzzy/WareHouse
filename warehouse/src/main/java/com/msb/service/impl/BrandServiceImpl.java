package com.msb.service.impl;

import com.msb.mapper.BrandMapper;
import com.msb.pojo.Brand;
import com.msb.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    //查询所有品牌
    @Override
    public List<Brand> queryAllBrand() {

        List<Brand> brandList = brandMapper.queryAllBrand();

        return brandList;
    }
}
