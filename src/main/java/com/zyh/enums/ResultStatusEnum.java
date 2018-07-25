package com.zyh.enums;

/**
 * @author zhangyanghui
 * @Title ResultStatusEnum
 * @Description
 * @date 2018/7/19 13:49
 */
public enum ResultStatusEnum {

    SUCCESS(0,"成功"),
    FAILURE(1,"失败"),
    UNKNOWN(-1,"未知错误"),

    PARAM_ERROR(100,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单取消状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单里没有商品"),
    PAY_STATUS_ERROR(17,"支付状态不正确"),
    CATE_EMPTY(18,"购物车为空"),
    OPENID_EMPTY(19,"买家微信openid为空"),
    ORDER_OWNER_ERROR(20,"此订单不属于当前用户"),
    WECHAT_OAUTH_ERROR(21,"微信网页授权错误"),
    PRODUCT_STATUS_ERROR(22,"商品状态不正确"),
    SELLER_INFO_NOT_EXIST(23,"卖家信息不存在"),
    LOGIN_ERROR(24,"登录失败，openid不正确");



    private Integer code;
    private String message;

    ResultStatusEnum(Integer code, String message) {
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
