package com.zyh.converter;

import com.alibaba.fastjson.JSONObject;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.entity.OrderDetail;
import com.zyh.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyanghui
 * @Title OrderForm2OrderDTOConverter
 * @Description
 * @date 2018/7/20 13:34
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO conver(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            orderDetails = JSONObject.parseArray(orderForm.getItems(),
                    OrderDetail.class);
        } catch (Exception e){
            log.error("对象转换报错...,{}",orderForm.getItems());
            throw new SellException(ResultStatusEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }
}
