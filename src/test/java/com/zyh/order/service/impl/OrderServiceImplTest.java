package com.zyh.order.service.impl;

import com.zyh.order.dto.OrderDTO;
import com.zyh.order.entity.OrderDetail;
import com.zyh.order.service.OrderService;
import com.zyh.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title OrderServiceImplTest
 * @Description
 * @date 2018/7/20 1:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private  final static String OPENID="zhangsan";
    private final static String ORDERID="2018072002105067427";

    @Autowired
    private OrderService orderService;


    @Test
    public void createOrder() throws Exception {

        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北京市海淀区中关村软件园");
        orderDTO.setBuyerName("张涛");
        orderDTO.setBuyerOpenid("token_zhangtao");
        orderDTO.setBuyerPhone("13502715524");


        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1234");
        orderDetail.setProductQuantity(2);

//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setProductId("1235");
//        orderDetail2.setProductQuantity(1);
        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setProductId("1236");
        orderDetail3.setProductQuantity(1);
        orderDetails.add(orderDetail);
//        orderDetails.add(orderDetail2);
        orderDetails.add(orderDetail3);
        orderDTO.setOrderDetails(orderDetails);
        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("创建订单：result={}",result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO=orderService.findOne(ORDERID);
        log.info("查询单个订单结果：result={}",orderDTO);
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);

        Page<OrderDTO> page=orderService.findAll(OPENID,pageRequest);

        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancelOrder() throws Exception {
        OrderDTO orderDTO = orderService.findOne("2018072010150966177");
        orderService.cancelOrder(orderDTO);
    }

    @Test
    public void finishOrder() throws Exception {
        OrderDTO orderDTO = orderService.findOne("2018072010182620347");
        orderService.finishOrder(orderDTO);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("2018072010182620347");
        orderService.paid(orderDTO);
    }

    @Test
    public void findList(){
        PageRequest pageRequest = new PageRequest(0,2);

        Page<OrderDTO> orderDTOPage = orderService.findAll(pageRequest);

        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}