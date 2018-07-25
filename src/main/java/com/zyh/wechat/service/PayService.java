package com.zyh.wechat.service;

import com.zyh.order.dto.OrderDTO;

/**
 * @author zhangyanghui
 * @Title PayService
 * @Description
 * @date 2018/7/21 18:49
 */
public interface PayService {

    void careate(OrderDTO orderDTO);
}
