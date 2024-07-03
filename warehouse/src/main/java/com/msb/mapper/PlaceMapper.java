package com.msb.mapper;

import com.msb.pojo.Place;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface PlaceMapper {

    //查询所有的产地
    List<Place> queryAllPlace();
}
