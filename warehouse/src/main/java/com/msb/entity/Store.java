package com.msb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {
    private Integer storeId;
    private String storeName;
    private String storeNum;
    private String storeAddress;
    private String concat;
    private String phone;
}
