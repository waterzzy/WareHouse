package com.msb.result;

/**
 * @author dh
 * @date 2024/7/3
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装ResultInfo对象   把执行操作成功或失败的（状态码code、提示信息msg、数据结果result）返回给前端提示
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultInfo {
    private Integer code=200; //默认设置的200====成功
    private String msg="success";
    private Object data;

}
