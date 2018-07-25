package com.zyh.order.dao;

import com.zyh.order.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title OrderMasterRepositoryTest
 * @Description
 * @date 2018/7/19 23:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private final static String OPENID="zhangsan";

    @Autowired
    private  OrderMasterRepository masterRepository;

    @Test
    public void save(){
        OrderMaster om=new OrderMaster();
        om.setOrderId("124");
        om.setBuyerName("张三");
        om.setBuyerPhone("13509128861");
        om.setBuyerAddress("北京大学第二教学楼");
        om.setBuyerOpenid("zhangsan");
        om.setOrderAmount(new BigDecimal(20));

        OrderMaster result=masterRepository.save(om);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest=new PageRequest(1,3);
        Page<OrderMaster> page=masterRepository.findByBuyerOpenid(OPENID,pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

}