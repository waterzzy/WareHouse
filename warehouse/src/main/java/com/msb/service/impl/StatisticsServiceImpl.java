package com.msb.service.impl;

import com.msb.mapper.StatisticsMapper;
import com.msb.pojo.Statistics;
import com.msb.service.StatisticsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/6
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

    /**
     * 统计各个仓库的库存数量
     * @return
     */
    @Override
    public List<Statistics> statisticsStoreInvent() {

        //统计各个仓库的库存的方法
        List<Statistics> statisticsList = statisticsMapper.statisticsStoreInvent();

        return statisticsList;
    }
}
