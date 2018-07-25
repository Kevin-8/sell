package com.zyh.order.dao;

import com.zyh.order.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title OrderDetailRepositoryTest
 * @Description
 * @date 2018/7/19 23:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save(){
        OrderDetail od=new OrderDetail();
        od.setOrderId("123");
        od.setDetailId("1235");
        od.setProductIcon("http://cxhgr.jpg");
        od.setProductId("1235");
        od.setProductName("川香回锅肉");
        od.setProductPrice(new BigDecimal(34.9));
        od.setProductQuantity(1);
        OrderDetail result=orderDetailRepository.save(od);
        Assert.assertNotEquals(null,result);
    }
    @Test
    public void findByOrderId() throws Exception {

        List<OrderDetail> orderDetails=orderDetailRepository.findByOrderId("123");
        System.out.println(orderDetails);
//        Assert.assertNotEquals(0,orderDetails.size());
    }

}