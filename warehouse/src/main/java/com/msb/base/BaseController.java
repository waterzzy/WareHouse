package com.msb.base;

import com.msb.result.ResultInfo;

/**
 * @author dh
 * @date 2024/7/3
 */
public class BaseController {
    public ResultInfo success(){
        return new ResultInfo();
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg,Object data){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }

    public ResultInfo success(Object data){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setData(data);
        return resultInfo;
    }
}
