package com.msb.controller;

import com.msb.pojo.Result;
import com.msb.pojo.Statistics;
import com.msb.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/6
 */
@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    /**
     * 查询各个仓库的 库存
     * @return
     */
    @RequestMapping("/store-invent")
    public Result statisticsStoreInvent(){

        //执行Service层统计各个仓库库存的方法
        List<Statistics> statisticsList = statisticsService.statisticsStoreInvent();

        //响应结果
        return Result.ok(statisticsList);
    }
}
