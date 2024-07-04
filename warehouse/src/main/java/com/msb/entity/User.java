package com.msb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer userId;//用户id

    private String userCode;//账号

    private String userName;//用户名

    private String userPwd;//用户密码

    private String userType;//用户类型

    private String userState;//用户状态 0 未审核  1 已经审核

    private String isDelete;//删除状态  0 正常 1 已经删除

    private int createBy;//创建人id

    //返回前端时,自动将Date转换成指定格式的json字符串
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    private int updateBy;//修改人id

    private Date updateTime;//修改时间

    private String getCode;//追加的属性--创建人

}
