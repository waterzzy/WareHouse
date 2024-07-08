package com.msb.service;

import com.msb.pojo.Result;
import com.msb.pojo.Statistics;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/6
 */
public interface StatisticsService {

    //查询所有仓库的库存数量
    List<Statistics> statisticsStoreInvent();
}
