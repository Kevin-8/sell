package com.zyh.product.service.impl;

import com.zyh.enums.ProductStatusEnum;
import com.zyh.product.entity.ProductInfo;
import com.zyh.product.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title ProductServiceImplTest
 * @Description
 * @date 2018/7/17 20:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductInfoService productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo=productService.findOne("1");
        Assert.assertEquals("1",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos=productService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findUpAll1() throws Exception {
        PageRequest request=new PageRequest(0,10);
        Page<ProductInfo> productInfos=productService.findUpAll(request);
        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void saveOrUpdate() throws Exception {
        ProductInfo product = new ProductInfo();
        product.setCategoryType(1);
        product.setProductDescription("非常好");
        product.setProductIcon("http://xxx.jpg");
        product.setProductId("12345");
        product.setProductPrice(new BigDecimal(49.8));
        product.setProductName("烤肉饭");
        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        product.setProductStock(120);
        ProductInfo result = productService.saveOrUpdate(product);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void onSale(){
        productService.onSale("1235");
    }

}