package com.zyh.order.controller;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zhangyanghui
 * @Title SellOrderController
 * @Description 卖家端订单
 * @date 2018/7/22 14:50
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "8") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOList = orderService.findAll(pageRequest);
        map.put("orderDTOPage",orderDTOList);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单操作
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancelOrder(@RequestParam("orderId") String orderId,
                                    Map<String,Object> map){
        map.put("url","/sell/seller/order/list");
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancelOrder(orderDTO);
        } catch (Exception e){
            map.put("msg", e.getMessage());

            return new ModelAndView("/common/error",map);
        }

        map.put("msg",ResultStatusEnum.SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }
    //订单详情
    @GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        map.put("url","/sell/seller/order/list");
        try {
            orderDTO = orderService.findOne(orderId);
            map.put("orderDTO",orderDTO);
        } catch (Exception e){
            map.put("msg", e.getMessage());

            return new ModelAndView("/common/error",map);
        }

    return new ModelAndView("/order/detail",map);
    }
    //完成订单
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        map.put("url","/sell/seller/order/list");
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finishOrder(orderDTO);
            map.put("orderDTO",orderDTO);
        } catch (Exception e){
            map.put("msg", e.getMessage());

            return new ModelAndView("/common/error",map);
        }
        map.put("msg",ResultStatusEnum.SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }
}
