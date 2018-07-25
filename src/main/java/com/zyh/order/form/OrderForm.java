package com.zyh.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author zhangyanghui
 * @Title OrderForm
 * @Description 表单验证
 * @date 2018/7/20 13:22
 */
@Data
public class OrderForm {

    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;
    //买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;
    //买家地址
    @NotEmpty(message = "买家地址必填")
    private String address;
    //买家微信openid
    @NotEmpty(message = "openid必填")
    private String openid;
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
