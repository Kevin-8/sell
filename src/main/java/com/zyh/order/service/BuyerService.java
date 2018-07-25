package com.zyh.order.service;

import com.zyh.order.dto.OrderDTO;

/**
 * @author zhangyanghui
 * @Title BuyerService
 * @Description
 * @date 2018/7/21 13:54
 */
public interface BuyerService {
    //查询订单
    OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
