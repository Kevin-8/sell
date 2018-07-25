package com.zyh.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zyh.enums.OrderStatusEnum;
import com.zyh.enums.PayStatusEnum;
import com.zyh.order.entity.OrderDetail;
import com.zyh.utils.EnumUtil;
import com.zyh.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyanghui
 * @Title OrderDTO
 * @Description
 * @date 2018/7/20 0:18
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    //买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //订单金额
    private BigDecimal orderAmount;
    //订单状态 默认新下的
    private Integer orderStatus;
    //支付状态 默认未支付
    private Integer payStatus;

    @JsonSerialize(using=Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using=Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetails;

    //忽略此方法字段
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
       return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
