package com.msb.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    //  根据商品ID增加商品库存
    public int addInventById(Integer productId, Integer invent);

}
