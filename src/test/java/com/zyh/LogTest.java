package com.zyh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyanghui
 * @Title LogTest
 * @Description
 * @date 2018/7/15 20:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class LogTest {

    private final Logger logger= LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test1(){
        logger.info("name={},age={}","zhangsan","16");
        logger.debug("debug...");
        logger.info("info...");
        logger.error("error...");
    }
}
