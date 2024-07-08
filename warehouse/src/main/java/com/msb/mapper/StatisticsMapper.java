package com.msb.mapper;

import com.msb.pojo.Statistics;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/6
 */
public interface StatisticsMapper {

    //统计各个仓库的库存数量
    List<Statistics> statisticsStoreInvent();

}
