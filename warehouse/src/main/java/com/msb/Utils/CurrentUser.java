package com.msb.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentUser {
    private Integer userId; //  用户ID
    private String userCode;    //用户名
    private String userName;    //真实姓名
}
