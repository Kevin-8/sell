package com.zyh.product.dao;

import com.zyh.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title ProductInfoRepositoryTest
 * @Description
 * @date 2018/7/15 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveProductTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId(System.currentTimeMillis()+"1");
        productInfo.setProductName("小炒肉");
        productInfo.setProductPrice(new BigDecimal(23.9));
        productInfo.setProductStock(120);
        productInfo.setProductDescription("非常nice");
        productInfo.setProductIcon("http://xiaochaorou.jpg");
        productInfo.setCategoryType(1);
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus(){

    }
}