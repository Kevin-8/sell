package com.zyh.converter;

import com.zyh.order.dto.OrderDTO;
import com.zyh.order.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyanghui
 * @Title OrderMaster2OrderDTOConverter
 * @Description
 * @date 2018/7/20 2:25
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e->
                convert(e)).collect(Collectors.toList());
    }
}
