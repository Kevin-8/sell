package com.zyh.exception;

import com.zyh.enums.ResultStatusEnum;

/**
 * @author zhangyanghui
 * @Title SellException
 * @Description
 * @date 2018/7/20 0:32
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultStatusEnum result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
