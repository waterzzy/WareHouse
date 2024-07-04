package com.msb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InStore {
    private Integer insId;      //  入库单ID
    private Integer storeId;    //  仓库ID
    private Integer productId;  //  产品ID
    private Integer inNum;      //  入库数量
    private Integer createBy;   //  创建入库单的用户ID
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;    //  创建时间
    private Integer isIn;       //  是否入库 1是，0否
    private String productName; //  商品名称
    private String startTime;   //  起始时间
    private String endTime;     //  结束时间
    private String storeName;   //  仓库名字
    private String userCode;    //  创建入库单的用户名字
    private BigDecimal inPrice; //  商品入库价格
}
