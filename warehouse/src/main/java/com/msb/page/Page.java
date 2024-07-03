package com.msb.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page {
    //当前页码
    private Integer pageNum;

    //每页显示行数
    private Integer pageSize;

    //总记录数
    private Integer totalNum;

    //存储当前页查询到的数据的List<?>集合
    private List<?> resultList;

}
