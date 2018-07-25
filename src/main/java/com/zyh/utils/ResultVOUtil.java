package com.zyh.utils;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.vo.ResultVO;

/**
 * @author zhangyanghui
 * @Title ResultVOUtil
 * @Description
 * @date 2018/7/19 22:43
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO result=new ResultVO();
        result.setData(object);
        result.setCode(ResultStatusEnum.SUCCESS.getCode());
        result.setMsg(ResultStatusEnum.SUCCESS.getMessage());
        return result;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO failure(Integer code, String msg){
        ResultVO result=new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
