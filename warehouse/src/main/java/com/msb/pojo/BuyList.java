package com.msb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 采购单表格buy_list的实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BuyList {
    private Integer buyId;  //采购单ID

    private Integer productId;  //采购单采购的商品ID

    private Integer storeId;    //采购单采购商品所在仓库的ID

    private Integer buyNum;     //预计采购的商品数量

    private Integer factBuyNum; //实际采购的数量
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buyTime;   //采购的时间

    private Integer supplyId;   //采购单采购的商品的供应商ID

    private Integer placeId;    //采购单采购商品的产地ID

    private String buyUser;     //采购人

    private String phone;       //采购人联系电话

    private String isIn;        //是否生产入库单 0否 1是

    /*追加查询所需属性   参数接收需要额外增加的属性，不在表格中 */
    private String productName; //商品名字
    private String storeName;   //仓库名字
    private String startTime; //搜索开始
    private String endTime; //搜索结束时间
}