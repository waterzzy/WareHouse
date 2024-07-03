package com.msb.service.impl;

import com.msb.mapper.PlaceMapper;
import com.msb.pojo.Place;
import com.msb.service.PlaceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Resource
    private PlaceMapper placeMapper;

    //查询所有的产地
    @Override
    public List<Place> queryAllPlace() {
        return placeMapper.queryAllPlace();
    }
}
