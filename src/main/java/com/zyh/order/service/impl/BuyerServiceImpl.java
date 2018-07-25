package com.zyh.order.service.impl;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.service.BuyerService;
import com.zyh.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.SelectableChannel;

/**
 * @author zhangyanghui
 * @Title BuyerServiceImpl
 * @Description
 * @date 2018/7/21 13:56
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(null==orderDTO){
            log.error("【订单取消】出错，查询不到此订单，orderId={}",orderId);
            throw new SellException(ResultStatusEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(null==orderDTO){
            return null;
        }
        if(!openid.equalsIgnoreCase(orderDTO.getBuyerOpenid())){
            log.error("【查询订单】openid不一致");
            throw new SellException(ResultStatusEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
