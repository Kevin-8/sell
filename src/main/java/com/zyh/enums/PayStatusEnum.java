package com.zyh.enums;

/**
 * @author zhangyanghui
 * @Title PayStatusEnum
 * @Description
 * @date 2018/7/19 23:26
 */
public enum PayStatusEnum implements CodeEnum{

    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
