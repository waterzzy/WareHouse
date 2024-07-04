package com.msb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库表单对应实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OutStore {
    private Integer outsId;     //  出库单ID
    private Integer productId;  //  出库商品ID
    private Integer storeId;   //  仓库ID
    private Integer tallyId;    //  理货员ID
    private BigDecimal outPrice;    //  商品出库价格
    private Integer outNum;     //  商品出库数量
    private Integer createBy;   //  创建出库单的用户ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;    //  出库单建立时间
    private String isOut;       //  出库状态 0未出库 1已出库
    /*  连表追加属性*/
    private String productName;     //  商品名称
    private String startTime;       //  起始时间
    private String endTime;         //  结束时间
    private String storeName;       //  仓库名称
    private String userCode;        //  创建出单的用户名称
}
