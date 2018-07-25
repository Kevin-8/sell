package com.zyh.vo;

import lombok.Data;

/**
 * @author zhangyanghui
 * @Title ResultVO
 * @Description HTTP请求返回的最外层对象
 * @date 2018/7/17 20:39
 */

public class ResultVO {
    //错误编号
    private Integer code;
    //提示
    private String msg;
    //具体对象
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
