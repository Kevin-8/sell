package com.zyh.order.entity;

import com.zyh.enums.OrderStatusEnum;
import com.zyh.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyanghui
 * @Title OrderMaster
 * @Description 订单主表
 * @date 2018/7/19 23:20
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    //订单id
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    //支付状态 默认未支付
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
