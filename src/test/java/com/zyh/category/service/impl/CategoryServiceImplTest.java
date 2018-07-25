package com.zyh.category.service.impl;

import com.zyh.category.entity.ProductCategory;
import com.zyh.category.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title CategoryServiceImplTest
 * @Description
 * @date 2018/7/15 22:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne(){
        ProductCategory productCategory=categoryService.findOne(10);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

}