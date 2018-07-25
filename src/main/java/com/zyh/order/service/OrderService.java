package com.zyh.order.service;

import com.zyh.order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhangyanghui
 * @Title OrderService
 * @Description
 * @date 2018/7/20 0:13
 */
public interface OrderService {
    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //查询订单
    OrderDTO findOne(String orderId);

    //查询订单列表
    Page<OrderDTO> findAll(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);

    //完成订单
    OrderDTO finishOrder(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

    //卖家端查询所有订单
    Page<OrderDTO> findAll(Pageable pageable);
}
