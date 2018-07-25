package com.zyh.logout.dao;

import com.zyh.logout.entity.SellerInfo;
import com.zyh.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title SellerInfoRepositoryTest
 * @Description
 * @date 2018/7/23 0:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;
    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("wx"+ KeyUtil.getRandomString(6));
        sellerInfo.setSellerId(KeyUtil.getRandomString(8));
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        repository.save(sellerInfo);
    }
    @Test
    public void findByOpenid() throws Exception {
        SellerInfo info = repository.findByOpenid("wx252967");
        List<String> a =new ArrayList<>();
        Long startTime = System.currentTimeMillis();
        log.info("start time is {}",startTime);
        for(int i=0;i<10000;i++){
            for (int j=0;j<10000;j++){
                a.add("b");
            }
            a.add("a");
        }
        Long endTime = System.currentTimeMillis();
        log.info("ent time is {}",endTime);
        log.info("总消耗时长{}",(endTime-startTime)/1000);
        Assert.assertNotEquals(null,info);
    }

}