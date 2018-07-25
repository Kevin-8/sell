package com.zyh.wechat.controller;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.service.OrderService;
import com.zyh.wechat.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyanghui
 * @Title PayController
 * @Description
 * @date 2018/7/21 18:46
 */
@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @PostMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){

        OrderDTO orderDTO = orderService.findOne(orderId);
        if(null==orderDTO){
            throw new SellException(ResultStatusEnum.ORDER_NOT_EXIST);
        }
        payService.careate(orderDTO);
    }
}
