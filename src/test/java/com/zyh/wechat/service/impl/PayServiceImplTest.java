package com.zyh.wechat.service.impl;

import com.zyh.order.dto.OrderDTO;
import com.zyh.order.service.OrderService;
import com.zyh.wechat.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title PayServiceImplTest
 * @Description
 * @date 2018/7/21 19:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void careate() throws Exception {
        OrderDTO orderDTO = orderService.findOne("2018072111220545466");
//        orderDTO.setBuyerOpenid();
        payService.careate(orderDTO);
    }

}